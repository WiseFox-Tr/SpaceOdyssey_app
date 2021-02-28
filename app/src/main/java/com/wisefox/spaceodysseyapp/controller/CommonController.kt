package com.wisefox.spaceodysseyapp.controller

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.view.CustomGraphicComponents


class CommonController {
    companion object {

        fun manageError(exception: Exception, context: Context): String {
            return when {
                exception.message?.contains("800", true) == true -> context.getString(R.string.errorLackOfQuestions)
                else -> context.getString(R.string.errorGeneric)
            }
        }

        fun displaySnackbar(rootView: View, message: String, context: Context) {
            val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            CustomGraphicComponents.setSnackbarStyle(snackbar = snackbar)
            snackbar.show()
        }
    }
}