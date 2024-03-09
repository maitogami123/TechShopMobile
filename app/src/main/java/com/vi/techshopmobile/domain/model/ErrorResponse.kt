package com.vi.techshopmobile.domain.model

data class ErrorResponse(
    val detail: String,
    val instance: String,
    val status: Int,
    val title: String,
    val type: String
)