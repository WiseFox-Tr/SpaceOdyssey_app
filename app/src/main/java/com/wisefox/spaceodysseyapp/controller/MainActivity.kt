package com.wisefox.spaceodysseyapp.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.quiz.QuizActivity


class MainActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewsAndInitContent()
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)

        //init Content
        tvTitle.text = getString(R.string.welcome)
        tvSubTitle.text = getString(R.string.whatToDo)
    }

    fun onClickQuizActivity(view: View) {
        val intentQuizActivity = Intent(this, QuizActivity::class.java)
        startActivity(intentQuizActivity)
    }
}
