package com.vi.techshopmobile.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val username: String,
) {
    constructor(
        text: String,
        username: String
    ) : this(0, text, username)
}