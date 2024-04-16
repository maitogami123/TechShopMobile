package com.vi.techshopmobile.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow


@Entity
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val searchString: String,
    val username: String,
){
    constructor(
        searchString: String,
        username: String,
    ) : this(0, searchString, username)
}