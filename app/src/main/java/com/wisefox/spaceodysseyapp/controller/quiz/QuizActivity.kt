package com.wisefox.spaceodysseyapp.controller.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wisefox.spaceodysseyapp.R

class QuizActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        findViewsAndInitContent()
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_sub_title)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = getString(R.string.whatWantToDo)
    }
}