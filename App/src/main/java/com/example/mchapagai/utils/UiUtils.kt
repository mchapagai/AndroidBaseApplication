package com.example.mchapagai.utils

import android.content.Context
import com.example.mchapagai.R
import com.example.mchapagai.response.ContributorResponse
import com.example.mchapagai.response.Contributors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/// Objects in Kotlin works similar to Java's static method
object UiUtils {

    fun rawContributors(context: Context): List<Contributors> {
        val listType = object : TypeToken<ContributorResponse?>() {}.type
        val response = Gson().fromJson<ContributorResponse>(readJSONFromRaw(context), listType)
        return response.contributors
    }

    private fun readJSONFromRaw(context: Context): String? {
        val json: String?
        try {
            val inputStream: InputStream = context.resources.openRawResource(R.raw.contributors)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun getContributors(context: Context): List<Contributors?> {
        val contributors = getJSONStringFromRaw(context, R.raw.contributors)
        val listType = object : TypeToken<ContributorResponse?>() {}.type
        val response = Gson().fromJson<ContributorResponse>(contributors, listType)
        return response.contributors
    }

    private fun getJSONStringFromRaw(context: Context, rawId: Int): String {
        val inputStream = context.resources.openRawResource(rawId)
        val buffer = BufferedReader(InputStreamReader(inputStream))
        var content = ""
        try {
            var s: String
            while (buffer.readLine().also { s = it } != null) {
                content += s
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content
    }
}