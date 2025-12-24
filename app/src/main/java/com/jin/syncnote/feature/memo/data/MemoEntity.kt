package com.jin.syncnote.feature.memo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val writeTime: Long,
    val updateTime: Long?,
    val favorite: Boolean
)
