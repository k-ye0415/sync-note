package com.jin.syncnote.feature.memo.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MemoTrackingDataSource {
    @Insert // temporary
    suspend fun insertMemo(memoEntity: MemoEntity)
}
