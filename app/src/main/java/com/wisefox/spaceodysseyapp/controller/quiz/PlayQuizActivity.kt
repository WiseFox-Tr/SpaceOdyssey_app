package com.wisefox.spaceodysseyapp.controller.quiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.model.Quiz
import com.wisefox.spaceodysseyapp.model.AppConst
import com.wisefox.spaceodysseyapp.model.Question

class PlayQuizActivity : AppCompatActivity(), View.OnClickListener {

    //layout elements
    private lateinit var layoutBtnAnswers: LinearLayout
    private lateinit var layoutTimer: LinearLayout
    private lateinit var layoutAnswerResult : LinearLayout
    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var btnAnswer1: Button
    private lateinit var btnAnswer2: Button
    private lateinit var btnAnswer3: Button
    private lateinit var btnAnswer4: Button
    private lateinit var timeRemainingSec: TextView
    private lateinit var pbTime: ProgressBar
    private lateinit var tvGoodOrBadAnswer: TextView
    private lateinit var tvScoreEvolution: TextView
    private lateinit var tvQuestionExplanation: TextView

    //Data
    private lateinit var quiz : Quiz

    private fun initialConfiguration() {
        //find views - layout
        layoutBtnAnswers = findViewById(R.id.layout_btnAnswers)
        layoutTimer = findViewById(R.id.layout_timer)
        layoutAnswerResult = findViewById(R.id.layout_answerResult)
        //find views - components
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_sub_title)
        tvQuestion = findViewById(R.id.tv_question_content)
        btnAnswer1 = findViewById(R.id.btn_answer1)
        btnAnswer2 = findViewById(R.id.btn_answer2)
        btnAnswer3 = findViewById(R.id.btn_answer3)
        btnAnswer4 = findViewById(R.id.btn_answer4)
        timeRemainingSec = findViewById(R.id.tv_timeRemainingSec)
        pbTime = findViewById(R.id.pb_timer)
        tvGoodOrBadAnswer = findViewById(R.id.tv_goodOrBadAnswer)
        tvScoreEvolution = findViewById(R.id.tv_scoreEvolution)
        tvQuestionExplanation = findViewById(R.id.tv_questionExplanation)


        //setListeners
        btnAnswer1.setOnClickListener(this)
        btnAnswer2.setOnClickListener(this)
        btnAnswer3.setOnClickListener(this)
        btnAnswer4.setOnClickListener(this)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = quiz.params.theme.theme_name
        layoutAnswerResult.visibility = GONE
    }

    private fun displayAQuestion(question: Question) {
        tvQuestion.text = question.quest_content
        btnAnswer1.text = question.quest_answer1
        btnAnswer2.text = question.quest_answer2
        btnAnswer3.text = question.quest_answer3
        btnAnswer4.text = question.quest_answer4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_quiz)
        quiz = intent.getSerializableExtra("quiz") as Quiz
        initialConfiguration()
        playQuiz()
    }


    private fun playQuiz() {
        val countQuestion = 0;

        //todo : implement loop for all questions
//        do {
//
//        } while (countQuestion < quiz.questions.size)


        //display questions
        displayAQuestion(quiz.questions[countQuestion])
        //after click, withdraw btn for answers
        //& display result
    }


    private fun verifyIfGoodAnswer(btnText : CharSequence) {
        val goodAnswer = quiz.questions[0].quest_answer1
        val isGoodAnswer: Boolean

        when (btnText) {
            goodAnswer -> isGoodAnswer = true
            else -> isGoodAnswer = false
        }
        showQuestionResult(isGoodAnswer)
    }

    //hides answerBtn & Timer and show result content
    fun showQuestionResult(isGoodAnswer: Boolean) {
        layoutBtnAnswers.visibility = GONE
        layoutTimer.visibility = GONE
        layoutAnswerResult.visibility = VISIBLE

        if(isGoodAnswer) {
            tvGoodOrBadAnswer.text = getString(R.string.goodAnswer)
            tvScoreEvolution.text = "+ 5 points !"
        } else {
            tvGoodOrBadAnswer.text = getString(R.string.badAnswer)
            tvScoreEvolution.text = getString(R.string.scoreEvolution)
        }
        tvQuestionExplanation.text = quiz.questions[0].quest_explanation
    }

    /* *******************
    --ON CLICK MANAGEMENT--
    ********************* */
    override fun onClick(v: View?) {
        when(v) {
            btnAnswer1 -> verifyIfGoodAnswer(btnAnswer1.text)
            btnAnswer2 -> verifyIfGoodAnswer(btnAnswer2.text)
            btnAnswer3 -> verifyIfGoodAnswer(btnAnswer3.text)
            btnAnswer4 -> verifyIfGoodAnswer(btnAnswer4.text)
        }
    }
}

