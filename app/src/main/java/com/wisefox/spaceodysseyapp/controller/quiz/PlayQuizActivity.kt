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
    private lateinit var tvQuestionNumber: TextView
    private lateinit var btnAnswer1: Button
    private lateinit var btnAnswer2: Button
    private lateinit var btnAnswer3: Button
    private lateinit var btnAnswer4: Button
    private lateinit var timeRemainingSec: TextView
    private lateinit var pbTime: ProgressBar
    private lateinit var tvGoodOrBadAnswer: TextView
    private lateinit var tvScoreEvolution: TextView
    private lateinit var tvQuestionExplanation: TextView
    private lateinit var btnNextQuestion: Button

    //Data
    private lateinit var quiz : Quiz

    //utils
    private var questionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_quiz)
        quiz = intent.getSerializableExtra("quiz") as Quiz
        initialConfiguration()
        displayAQuestion(quiz.questions[0])
    }

    /** this method find views by id, set on click listeners and init initial content independently of question played. **/
    private fun initialConfiguration() {
        //find views - layout
        layoutBtnAnswers = findViewById(R.id.layout_btnAnswers)
        layoutTimer = findViewById(R.id.layout_timer)
        layoutAnswerResult = findViewById(R.id.layout_answerResult)
        //find views - components
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)
        tvQuestion = findViewById(R.id.tv_questionContent)
        tvQuestionNumber = findViewById(R.id.tv_questionNumber)
        btnAnswer1 = findViewById(R.id.btn_answer1)
        btnAnswer2 = findViewById(R.id.btn_answer2)
        btnAnswer3 = findViewById(R.id.btn_answer3)
        btnAnswer4 = findViewById(R.id.btn_answer4)

        timeRemainingSec = findViewById(R.id.tv_timeRemainingSec)
        pbTime = findViewById(R.id.pb_timer)
        tvGoodOrBadAnswer = findViewById(R.id.tv_goodOrBadAnswer)
        tvScoreEvolution = findViewById(R.id.tv_scoreEvolution)
        tvQuestionExplanation = findViewById(R.id.tv_questionExplanation)
        btnNextQuestion = findViewById(R.id.btn_nextQuestion)

        //setListeners
        btnAnswer1.setOnClickListener(this)
        btnAnswer2.setOnClickListener(this)
        btnAnswer3.setOnClickListener(this)
        btnAnswer4.setOnClickListener(this)
        btnNextQuestion.setOnClickListener(this)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = quiz.params.theme.theme_name
    }

    private fun verifyIfGoodAnswer(btnText : CharSequence) {
        val goodAnswer = quiz.questions[questionIndex].quest_answer1
        val isGoodAnswer: Boolean

        when (btnText) {
            goodAnswer -> isGoodAnswer = true
            else -> isGoodAnswer = false
        }
        showQuestionResult(isGoodAnswer)
    }

    /** this method hides answerBtn & Timer and show result content
     *
     * @param[isGoodAnswer] it takes a boolean as params
     * **/
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
        tvQuestionExplanation.text = quiz.questions[questionIndex].quest_explanation
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
            //change question when user click on this question
            btnNextQuestion -> {
                if(questionIndex < quiz.questions.size - 1) {
                    questionIndex++
                    layoutBtnAnswers.visibility = VISIBLE
                    layoutTimer.visibility = VISIBLE
                    layoutAnswerResult.visibility = GONE
                    displayAQuestion(quiz.questions[questionIndex])
                }
                else {
                    Log.w(AppConst.TAG_CONTROLLER, "Quiz is over!")
                    //todo : display quiz result
                }
            }
        }
    }


    /* *******************
    --GRAPHICAL METHODS--
    ********************* */

    /** display the content of a question : the question itself, this number in question list and it's possible answers
     *
     * @param[question] this method take the question to display as param
     * **/
    private fun displayAQuestion(question: Question) {
        tvQuestion.text = question.quest_content
        tvQuestionNumber.text = (questionIndex + 1).toString()
        btnAnswer1.text = question.quest_answer1
        btnAnswer2.text = question.quest_answer2
        btnAnswer3.text = question.quest_answer3
        btnAnswer4.text = question.quest_answer4
    }
}

