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
import com.wisefox.spaceodysseyapp.view.CustomGraphicComponents


class CommonController {
    companion object {

        fun manageError(exception: Exception, context: Context): String {
            return when {
                exception.message?.contains("800", true) == true -> context.getString(R.string.errorLackOfQuestions)
                else -> context.getString(R.string.errorGeneric)
            }
        }

        fun displaySnackbar(rootView: View, message: String) {
            val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            CustomGraphicComponents.setSnackbarStyle(snackbar = snackbar)
            snackbar.show()
        }

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