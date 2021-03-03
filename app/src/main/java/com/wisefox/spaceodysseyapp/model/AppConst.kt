package com.wisefox.spaceodysseyapp.model

class AppConst {
    companion object {
        //TAG Logs
        const val TAG_WS = "WSUtils"
        const val TAG_CONTROLLER = "Controller"

        //URL
        private const val URL_ROOT_API = "http://10.0.2.2:8080"
        const val URL_API_GET_PARAMS = "$URL_ROOT_API/getParams"
        const val URL_API_GET_QUESTIONS = "$URL_ROOT_API/getQuestions"

        //score multiplier
        const val scoreMultiplierEasy = 2
        const val scoreMultiplierMedium = 4
        const val scoreMultiplierConfirmed = 7
    }
}