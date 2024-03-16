package com.vi.techshopmobile.domain.model

data class UserToken(
    val exp: Int = 0,
    val iat: Int = 0,
    val sub: String = ""
)