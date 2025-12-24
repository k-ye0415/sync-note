package com.jin.syncnote

import android.content.Context
import com.jin.syncnote.feature.memo.data.MemoRepositoryImpl
import com.jin.syncnote.feature.memo.domain.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppContainer(private val context: Context) {
    private val database: AppDatabase by lazy { AppDatabase.getInstance(context) }

    val memoRepository: MemoRepository by lazy {
        MemoRepositoryImpl(database.memoTrackingDataSource())
    }

    suspend fun preWarmDatabase() {
        withContext(Dispatchers.IO) {
            database.openHelper.writableDatabase // 강제로 DB 파일을 열어 연결 확립
        }
    }
}
