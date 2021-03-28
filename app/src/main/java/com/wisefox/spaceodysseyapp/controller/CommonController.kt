package com.wisefox.spaceodysseyapp.controller

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.snackbar.Snackbar
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.account.LogInActivity
import com.wisefox.spaceodysseyapp.controller.quiz.QuizActivity
import com.wisefox.spaceodysseyapp.model.AppConst
import com.wisefox.spaceodysseyapp.view.CustomGraphicComponents


class CommonController {
    companion object {

        /** returns a custom message to display in snackbar when an error occurs */
        fun manageError(exception: Exception, context: Context): String {
            return when {
                exception.message?.contains("800", true) == true -> context.getString(R.string.errorLackOfQuestions)
                exception.message?.contains(AppConst.EXCEPTION_EMPTY_FIELD, true) == true -> context.getString(R.string.errorEmptyField)
                exception.message?.contains(AppConst.EXCEPTION_LENGTH_OVERFLOW, true) == true -> context.getString(R.string.errorLengthOverflow)
                exception.message?.contains(AppConst.EXCEPTION_MAIL_FORMAT, true) == true -> context.getString(R.string.errorMailFormat)
                exception.message?.contains(AppConst.EXCEPTION_PASSWORD_FORMAT, true) == true -> context.getString(R.string.errorPasswordFormat)
                exception.message?.contains(AppConst.EXCEPTION_PASSWORDS_DO_NOT_MATCH, true) == true -> context.getString(R.string.errorPasswordCorrespondence)
                else -> context.getString(R.string.errorGeneric) //default message
            }
        }

        fun displaySnackbar(rootView: View, message: String) {
            val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            CustomGraphicComponents.setSnackbarStyle(snackbar = snackbar)
            snackbar.show()
        }

        /** specify instruction for each appbar menu items when one of them is clicked */
        fun itemSelectedCallback(item: MenuItem, context: Context) {
            when(item.itemId) {
                R.id.menu_account -> {
                    val intentLogInActivity = Intent(context, LogInActivity::class.java)
                    startActivity(context, intentLogInActivity, null)
                }
                R.id.menu_mainMenu ->  {
                    val intentMainActivity = Intent(context, MainActivity::class.java)
                    startActivity(context, intentMainActivity, null)
                }
                R.id.menu_lesson ->  Toast.makeText(context, "Lesson clicked", Toast.LENGTH_SHORT).show()
                R.id.menu_quiz ->  {
                    val intentQuizActivity = Intent(context, QuizActivity::class.java)
                    startActivity(context, intentQuizActivity, null)
                }
                R.id.menu_about ->  Toast.makeText(context, "About clicked", Toast.LENGTH_SHORT).show()
            }
            //todo: add for each activity a finish()
        }
    }
}