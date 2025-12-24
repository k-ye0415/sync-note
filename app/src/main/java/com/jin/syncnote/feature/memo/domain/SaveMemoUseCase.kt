package com.jin.syncnote.feature.memo.domain

class SaveMemoUseCase(private val memoRepository: MemoRepository) {
    suspend operator fun invoke(memo: Memo): Result<Unit> {
        return memoRepository.saveMemo(memo)
    }
}
