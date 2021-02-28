package com.wisefox.spaceodysseyapp.controller.quiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.model.Quiz
import com.wisefox.spaceodysseyapp.model.AppConst

class PlayQuizActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button

    //Data
    private lateinit var quiz : Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_quiz)
        quiz = intent.getSerializableExtra("quiz") as Quiz
        findViewsAndInitContent()

        Log.d(AppConst.TAG_CONTROLLER, "LIST OF QUESTIONS RETRIEVED BY PLAY A QUIZ ACTIVITY : \n\n")
        for(i in quiz.questions.indices) {
            Log.d(AppConst.TAG_CONTROLLER, "question ${i+1}: --> ${quiz.questions[i]}")
        }
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_sub_title)
        tvQuestion = findViewById(R.id.tv_question_content)
        btn1 = findViewById(R.id.btnAnswer1)
        btn2 = findViewById(R.id.btnAnswer2)
        btn3 = findViewById(R.id.btnAnswer3)
        btn4 = findViewById(R.id.btnAnswer4)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = quiz.params.theme.theme_name
        tvQuestion.text = quiz.questions[0].quest_content
        btn1.text = quiz.questions[0].quest_answer1
        btn2.text = quiz.questions[0].quest_answer2
        btn3.text = quiz.questions[0].quest_answer3
        btn4.text = quiz.questions[0].quest_answer4
    }
}