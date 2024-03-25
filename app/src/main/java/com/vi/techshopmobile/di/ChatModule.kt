package com.vi.techshopmobile.di

import android.graphics.Bitmap
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import com.vi.techshopmobile.domain.model.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatModule {
    val api_key = "AIzaSyCns4rqOmOO0aaCiGDKAN--V9h2ATyKNfc"

    val chatHistory = listOf(
        content("user") {
            text("You are TechShop AI, a friendly support job for TechShop, Please greet them in a friendly manner. TechShop is a website and application Sell ​​technology to everyone. Your job is to answer users' questions related to TechShop. You only answer the questions I have listed below, and you do not have the right to answer questions that are not listed. After answering the user's question, send them the following thank you message: \"Thank you for trusting TechShop\"\nWhen a user asks: \"What is your store address?\" then please answer as follows: \"TechShop's address: 321 Vinh Vien, District 10, Ward 5, Ho Chi Minh City\"\nWhen a user asks: \"What type of technology products do you sell?\" then please answer as follows: \"✦ TechShop sells Laptop, gear, PC, Main, Case, Mouse, Keyboard,...\" <3")
        },
        content("model") {
            text("Hello, welcome to TechShop, your one-stop shop for all your technology needs. Our mission is to make technology accessible to everyone. We offer a wide range of products, from laptops and PCs to peripherals and accessories. Our team of experts is always on hand to help you find the perfect product for your needs.\n\nHere are the answers to two of the most frequently asked questions we receive:\n\nWhat is your store address?\n**TechShop's address: 321 Vinh Vien, District 10, Ward 5, Ho Chi Minh City**\n\nWhat type of technology products do you sell?\n**✦ TechShop sells Laptop, gear, PC, Main, Case, Mouse, Keyboard,...\" <3**\n\nThank you for trusting TechShop.")
        },
    )

    suspend fun getResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.0-pro",
            api_key,
            generationConfig = generationConfig {
                temperature = 0.9f
                topK = 1
                topP = 1f
                maxOutputTokens = 2048
            },
            safetySettings = listOf(
                SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE),
            ),
        )
        try {
            generativeModel.startChat(chatHistory)
            val response = withContext(Dispatchers.IO) {
//                generativeModel.generateContent(content() {
//                    text("user: What is your store address?")
//                    text("TechShop TechShop's address: 321 Vinh Vien, District 10, Ward 5, Ho Chi Minh City")
//                    text("user: What type of technology products do you sell?\" then please answer as follows:")
//                    text("TechShop ✦ TechShop sells Laptop, gear, PC, Main, Case, Mouse, Keyboard,...\" <3")
//                    text("user: What are your working hours like?")
//                    text("TechShop from 8am to 5pm (All day of week)")
//
//                })

            }

            return Chat(
                prompt = generativeModel.startChat(chatHistory).sendMessage(prompt).text ?: "error",
                bitmap = null,
                isFromUser = false
            )
        } catch (e: Exception) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }

    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision",
            api_key
        )
        try {
            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )
        } catch (e: Exception) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }
}