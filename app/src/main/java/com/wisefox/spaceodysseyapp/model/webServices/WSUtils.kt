package com.wisefox.spaceodysseyapp.model.webServices

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wisefox.spaceodysseyapp.model.ParamsBean
import com.wisefox.spaceodysseyapp.model.QuestionBean
import com.wisefox.spaceodysseyapp.model.ResponseCodeBean
import com.wisefox.spaceodysseyapp.model.webServices.OkHttp.Companion.sendPostOkHttpRequest
import com.wisefox.spaceodysseyapp.utils.Const

class WSUtils {

    companion object {
        private val gson = Gson()
        private var jsonToSend = ""

        //method getQuestions --> send params to server (theme & level) and retrieve a list of questions
        fun getQuestions(params: ParamsBean): List<QuestionBean> {

            jsonToSend = gson.toJson(params)
            val jsonReceived = sendPostOkHttpRequest(Const.URL_API_GET_QUESTIONS, jsonToSend)

            val itemType = object : TypeToken<ResponseCodeBean<List<QuestionBean>>>() {}.type
            val responseCodeBean = gson.fromJson<ResponseCodeBean<List<QuestionBean>>>(jsonReceived, itemType)

            Log.d(Const.TAG_WS, "method getQuestions : --> responseCode = $responseCodeBean")
            when {
                responseCodeBean.code != 200 -> throw Exception(responseCodeBean.message ?: "An error occurred...")
                responseCodeBean.data.isNullOrEmpty() -> throw Exception("Empty or nonexistent list")
                else -> return responseCodeBean.data //List<QuestionBean>
            }
        }
    }
}
