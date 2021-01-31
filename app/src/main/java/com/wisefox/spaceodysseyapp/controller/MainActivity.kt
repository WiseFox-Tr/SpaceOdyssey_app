package com.wisefox.spaceodysseyapp.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.quiz.QuizActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickQuizActivity(view: View) {
        val intentQuizActivity = Intent(this, QuizActivity::class.java)
        startActivity(intentQuizActivity)
    }
}
