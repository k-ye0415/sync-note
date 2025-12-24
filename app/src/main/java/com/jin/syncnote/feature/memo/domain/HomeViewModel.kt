package com.jin.syncnote.feature.memo.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jin.syncnote.ui.state.DbState
import com.jin.syncnote.ui.state.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getMemoUseCase: GetMemoUseCase) :
    ViewModel() {

    private val _memoListState = MutableStateFlow<UiState<List<Memo>>>(UiState.Loading)
    val memoListState: StateFlow<UiState<List<Memo>>> = _memoListState

    // temporary
    private val _insertState = MutableSharedFlow<DbState<Unit>>()
    val insertState = _insertState.asSharedFlow()

    init {
        launchMemList()
    }

    private fun launchMemList() {
        viewModelScope.launch {
            _memoListState.value = getMemoUseCase().fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message.orEmpty()) }
            )
        }
    }

}
