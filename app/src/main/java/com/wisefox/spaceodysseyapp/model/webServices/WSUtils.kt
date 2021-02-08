package com.wisefox.spaceodysseyapp.model.webServices

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wisefox.spaceodysseyapp.model.Params
import com.wisefox.spaceodysseyapp.model.QuestionBean
import com.wisefox.spaceodysseyapp.model.ResponseCode
import com.wisefox.spaceodysseyapp.model.webServices.OkHttp.Companion.sendPostOkHttpRequest
import com.wisefox.spaceodysseyapp.utils.Const

class WSUtils {

    companion object {
        private val gson = Gson()
        private var jsonToSend = ""

        //method getQuestions --> send params to server (theme & level) and retrieve a list of questions
        fun getQuestions(params: Params): List<QuestionBean> {

            jsonToSend = gson.toJson(params)
            val jsonReceived = sendPostOkHttpRequest(Const.URL_API_GET_QUESTIONS, jsonToSend)

            val itemType = object : TypeToken<ResponseCode<List<QuestionBean>>>() {}.type
            val responseCodeBean = gson.fromJson<ResponseCode<List<QuestionBean>>>(jsonReceived, itemType)

            Log.d(Const.TAG_WS, "method getQuestions : --> responseCode = $responseCodeBean")

            val questionsList = responseCodeBean.data

            when {
                responseCodeBean.code != 200 -> throw Exception(responseCodeBean.message ?: "An error occurred...")
                questionsList.isNullOrEmpty() -> throw Exception("Empty or nonexistent list")
                else -> return questionsList
            }
        }
    }
}
