package com.wisefox.spaceodysseyapp.controller

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.view.CustomizeGraphicsComponent


class ControllerUtils {
    companion object {

        fun manageError(exception: Exception, context: Context): String {
            return when {
                exception.message?.contains("Not enough questions", true) == true -> context.getString(R.string.errorLackOfQuestions)
                else -> context.getString(R.string.errorGeneric)
            }
        }

        fun displaySnackbar(rootView: View, message: String, context: Context) {
            val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            CustomizeGraphicsComponent.setSnackbarStyle(snackbar = snackbar)
            snackbar.show()
        }
    }
}