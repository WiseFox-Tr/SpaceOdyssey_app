package com.wisefox.spaceodysseyapp.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody


class OkHttp {
    companion object {
        private val client = OkHttpClient()
        private val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()

        @Throws(Exception::class)
        fun sendGetOkHttpRequest(url: String): String {
            println("url : $url")

            //request creation & request execution
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            //return's code analysis
            return if (response.code != 200) {
                throw Exception("Incorrect server response : code ${response.code}")
            }
            //request result
            else {
                response.body?.string() ?: ""
            }
        }

        @Throws(Exception::class)
        fun sendPostOkHttpRequest(url: String, jsonToSend: String): String {
            println("url : $url")

            //body request
            val body = jsonToSend.toRequestBody(MEDIA_TYPE_JSON)

            //request creation & request execution
            val request = Request.Builder().url(url).post(body).build()
            val response = client.newCall(request).execute()
            //return's code analysis
            return if (response.code != 200) {
                throw Exception("Incorrect server response : code ${response.code}")
            }
            //request result
            else {
                response.body?.string() ?: ""
            }
        }
    }
}


