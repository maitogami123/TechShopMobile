package com.vi.techshopmobile.presentation.chatAI

import android.graphics.Bitmap
import com.vi.techshopmobile.domain.model.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)