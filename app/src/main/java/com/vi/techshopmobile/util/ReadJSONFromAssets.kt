package com.vi.techshopmobile.util

import android.content.Context
import android.util.Log
import com.vi.techshopmobile.domain.model.Provinces
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

fun ReadJSONFromAssets(context: Context, path: String): String {
    val identifier = "[ReadJSON]"
    try {
        val file = context.assets.open("$path")
//        Log.i(
//            identifier,
//            "${DebuggingIdentifiers.actionOrEventSucceded} Found File: $file.",
//        )
        val bufferedReader = BufferedReader(InputStreamReader(file))

        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }
        Log.i(
            identifier,
            "getJSON} stringBuilder: $stringBuilder.",
        )
        val jsonString = stringBuilder.toString()
        Log.i(
            identifier,
            " JSON as String: $jsonString.",
        )
        return jsonString
    } catch (e: Exception) {
        Log.e("errorWwhat", e.toString()
//            identifier,
//            "${DebuggingIdentifiers.actionOrEventFailed} Error reading JSON: $e.",
        )
        e.printStackTrace()
        return ""
    }
}