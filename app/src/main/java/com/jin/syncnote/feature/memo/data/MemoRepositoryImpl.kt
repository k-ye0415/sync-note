package com.jin.syncnote.feature.memo.data

import com.jin.syncnote.feature.home.ui.dummyMemo
import com.jin.syncnote.feature.memo.domain.Memo
import com.jin.syncnote.feature.memo.domain.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset

class MemoRepositoryImpl(private val memoTrackingDataSource: MemoTrackingDataSource) : MemoRepository {
    override fun fetchMemoList(): Result<List<Memo>> {
        return Result.success(dummyMemo)
    }

    override suspend fun saveMemo(memo: Memo): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                memoTrackingDataSource.insertMemo(memo.toEntity())
                Result.success(Unit)
            }
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    private fun Memo.toEntity(): MemoEntity {
        return MemoEntity(
            id = id ?: 0,
            title = title,
            content = content,
            writeTime = writeTime.toUtcMillis(),
            updateTime = updateTime.toUtcMillis(),
            favorite = favorite
        )
    }

    private fun LocalDate.toUtcMillis(atTime: LocalTime = LocalTime.NOON): Long {
        return this
            .atTime(atTime)
            .atZone(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()
    }
}
