package com.wisefox.spaceodysseyapp.controller.quiz

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.model.Quiz
import com.wisefox.spaceodysseyapp.utils.Const

class PlayQuizActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView

    //Data
    private lateinit var quiz : Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_quiz)
        findViewsAndInitContent()

        quiz = intent.getSerializableExtra("quiz") as Quiz

        Log.d(Const.TAG_CONTROLLER, "LIST OF QUESTIONS RETRIEVED BY PLAY A QUIZ ACTIVITY : \n\n")
        for(i in quiz.questions.indices) {
            Log.d(Const.TAG_CONTROLLER, "question ${i}: --> ${quiz.questions[i]}")
        }
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_sub_title)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = "Thème : Système solaire"
    }
}