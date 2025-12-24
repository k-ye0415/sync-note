package com.jin.syncnote.feature.memo.domain

import java.time.LocalDate

data class Memo(
    val id: Int?,
    val title: String,
    val content: String,
    val writeTime: LocalDate,
    val updateTime: LocalDate,
    val favorite: Boolean
)
