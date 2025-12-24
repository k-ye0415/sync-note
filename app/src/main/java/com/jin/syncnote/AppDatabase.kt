package com.jin.syncnote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jin.syncnote.feature.memo.data.MemoEntity
import com.jin.syncnote.feature.memo.data.MemoTrackingDataSource

@Database(entities = [MemoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoTrackingDataSource(): MemoTrackingDataSource

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
