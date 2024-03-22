package com.vi.techshopmobile.presentation.chatAI

import android.graphics.Bitmap

sealed class ChatEvent {
    data class UpdatePrompt(val newPrompt: String) : ChatEvent()
    data class SendPrompt(val prompt: String, val bitmap: Bitmap?) : ChatEvent()
}