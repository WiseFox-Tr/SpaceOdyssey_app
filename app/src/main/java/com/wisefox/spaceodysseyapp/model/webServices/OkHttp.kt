package com.wisefox.spaceodysseyapp.model.webServices

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
            return if (response.code < 200 || response.code >= 300) {
                throw Exception("Réponse du serveur incorrecte : " + response.code)
            }
            //request result
            else {
                response.body?.string() ?: ""
            }
        }

        @Throws(Exception::class)
        fun sendPostOkHttpRequest(url: String, paramJson: String): String {
            println("url : $url")

            //body request
            val body = paramJson.toRequestBody(MEDIA_TYPE_JSON)

            //request creation & request execution
            val request = Request.Builder().url(url).post(body).build()
            val response = client.newCall(request).execute()
            //return's code analysis
            return if (response.code < 200 || response.code >= 300) {
                throw Exception("Réponse du serveur incorrect : " + response.code)
            }
            //request result
            else {
                response.body?.string() ?: ""
            }
        }
    }
}


