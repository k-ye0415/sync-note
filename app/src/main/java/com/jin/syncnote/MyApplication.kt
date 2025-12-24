package com.jin.syncnote

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)

        CoroutineScope(Dispatchers.IO).launch {
            container.preWarmDatabase()
            // temporary
//            for (memo in dummyMemo) {
//                container.memoRepository.saveMemo(memo)
//            }
        }
    }
}
