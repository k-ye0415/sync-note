package com.jin.syncnote.feature.memo.domain

class GetMemoUseCase(private val memoRepository: MemoRepository) {
    operator fun invoke(): Result<List<Memo>> {
        return memoRepository.fetchMemoList()
    }
}
