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

        //score multiplier for quiz
        const val scoreMultiplierEasy = 2
        const val scoreMultiplierMedium = 4
        const val scoreMultiplierConfirmed = 7

        //max length for editText
        const val maxPseudoLength = 25
        const val maxMailLength = 200
        const val maxPasswordLength = 30

        //exception text values
        const val EXCEPTION_EMPTY_FIELD = "Edit text content is empty"
        const val EXCEPTION_LENGTH_OVERFLOW = "Edit text content length exceed max length"
        const val EXCEPTION_MAIL_FORMAT = "Mail format is invalid"
        const val EXCEPTION_PASSWORD_FORMAT = "Password format is invalid"
        const val EXCEPTION_PASSWORDS_DO_NOT_MATCH = "Passwords don't match"
    }
}