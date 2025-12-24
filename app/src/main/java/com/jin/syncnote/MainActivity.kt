package com.jin.syncnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.jin.syncnote.feature.home.ui.MemoHomeScreen
import com.jin.syncnote.feature.memo.domain.GetMemoUseCase
import com.jin.syncnote.feature.memo.domain.HomeViewModel
import com.jin.syncnote.feature.memo.domain.SaveMemoUseCase
import com.jin.syncnote.ui.theme.SyncNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = (application as MyApplication).container
        enableEdgeToEdge()
        setContent {
            SyncNoteTheme {
                AppNavigator(container)
            }
        }
    }
}

@Composable
fun AppNavigator(container: AppContainer) {
    val homeViewModel = remember {
        HomeViewModel(getMemoUseCase = GetMemoUseCase(container.memoRepository))
    }
    MemoHomeScreen(homeViewModel)
}
