package com.wisefox.spaceodysseyapp.model.webServices

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wisefox.spaceodysseyapp.model.AppConst
import com.wisefox.spaceodysseyapp.model.Params
import com.wisefox.spaceodysseyapp.model.Question
import com.wisefox.spaceodysseyapp.model.ServerResponse
import com.wisefox.spaceodysseyapp.utils.OkHttp

class WebServices {
    companion object {
        private val gson = Gson()
        private var jsonToSend = ""
        private var jsonReceived = ""

        fun getParams(): Params {
            jsonReceived = OkHttp.sendGetOkHttpRequest(AppConst.URL_API_GET_PARAMS)

            val itemType = object : TypeToken<ServerResponse<Params>>() {}.type
            val responseCodeBean = gson.fromJson<ServerResponse<Params>>(jsonReceived, itemType)

            Log.v(AppConst.TAG_WS, "method getParams : --> responseCode = $responseCodeBean")
            when {
                responseCodeBean.code != 200 -> throw Exception("error ${responseCodeBean.code} : ${responseCodeBean.message}")
                responseCodeBean.data == null -> throw Exception("No content in data")
                else -> return responseCodeBean.data //List<QuestionBean>
            }

        }

        //method getQuestions --> send params to server (theme & level) and retrieve a list of questions
        fun getQuestions(params: Params): List<Question> {

            jsonToSend = gson.toJson(params)
            jsonReceived = OkHttp.sendPostOkHttpRequest(AppConst.URL_API_GET_QUESTIONS, jsonToSend)

            val itemType = object : TypeToken<ServerResponse<List<Question>>>() {}.type
            val responseCodeBean = gson.fromJson<ServerResponse<List<Question>>>(jsonReceived, itemType)

            Log.v(AppConst.TAG_WS, "method getQuestions : --> responseCode = $responseCodeBean")
            when {
                responseCodeBean.code != 200 -> throw Exception("error ${responseCodeBean.code} : ${responseCodeBean.message}")
                responseCodeBean.data.isNullOrEmpty() -> throw Exception("No content in data")
                else -> return responseCodeBean.data //List<QuestionBean>
            }
        }
    }
}
