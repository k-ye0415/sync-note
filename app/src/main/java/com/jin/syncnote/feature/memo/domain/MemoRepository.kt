package com.jin.syncnote.feature.memo.domain

interface MemoRepository {
    fun fetchMemoList(): Result<List<Memo>>
    suspend fun saveMemo(memo: Memo): Result<Unit>
}
