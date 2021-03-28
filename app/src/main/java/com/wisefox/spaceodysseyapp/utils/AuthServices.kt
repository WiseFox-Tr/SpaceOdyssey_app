package com.wisefox.spaceodysseyapp.utils

import android.util.Patterns
import com.wisefox.spaceodysseyapp.model.AppConst
import java.util.regex.Pattern

class AuthServices {

    companion object {
        /** Check the string in the edit text and throw exception only if it is not empty or if exceed the max length */
        fun checkLengthContentEditText(editTextString: String, maxLength: Int) {
            if (editTextString.isEmpty())
                throw Exception(AppConst.EXCEPTION_EMPTY_FIELD)
            if (editTextString.length >= maxLength)
                throw Exception(AppConst.EXCEPTION_LENGTH_OVERFLOW)
            else
                println("for : '$editTextString', length is valid")
        }

        /** Check the mail format */
        fun checkMailFormat(email: String) {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                throw Exception(AppConst.EXCEPTION_MAIL_FORMAT)
            else
                println("mail format is valid")
        }

        /** regex Password -> check the password format */
        fun checkPasswordFormat(password: String) {
            val passwordREGEX = Pattern.compile(
                    "^" +
                            "(?=.*[0-9])" +         //at least 1 digit
                            "(?=.*[a-z])" +         //at least 1 lower case letter
                            "(?=.*[A-Z])" +         //at least 1 upper case letter
                            "(?=.*[a-zA-Z])" +      //any letter
                            "(?=.*[@#$%^&+=!?/])" +    //at least 1 special character
                            "(?=\\S+$)" +           //no white spaces
                            ".{6,}" +               //at least 6 characters
                            "$"
            )
            if(!passwordREGEX.matcher(password).matches())
                throw Exception(AppConst.EXCEPTION_PASSWORD_FORMAT)
            else
                println("password format is valid")

        }

        /** Check that password & password confirmation are same */
        fun checkCorrespondenceBetweenPasswords(password: String, passwordConfirmation: String) {
            if(password != passwordConfirmation)
                throw Exception(AppConst.EXCEPTION_PASSWORDS_DO_NOT_MATCH)
            else
                println("passwords are identical")
        }
    }
}