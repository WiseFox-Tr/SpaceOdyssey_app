package com.wisefox.spaceodysseyapp.controller.quiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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
import com.wisefox.spaceodysseyapp.controller.MainActivity
import com.wisefox.spaceodysseyapp.model.AppConst
import com.wisefox.spaceodysseyapp.model.Question
import com.wisefox.spaceodysseyapp.model.Quiz


class PlayQuizActivity : AppCompatActivity(), View.OnClickListener {

    enum class TimerState { Stopped, Running, Over }

    //layout elements
    private lateinit var layoutBtnAnswers: LinearLayout
    private lateinit var layoutTimer: LinearLayout
    private lateinit var layoutResultAnswer: LinearLayout
    private lateinit var layoutQuizState: LinearLayout
    private lateinit var layoutResultQuiz: LinearLayout
    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var tvQuizStateCurrentScore: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuizStateMaxQuestion: TextView
    private lateinit var btnAnswer1: Button
    private lateinit var btnAnswer2: Button
    private lateinit var btnAnswer3: Button
    private lateinit var btnAnswer4: Button
    private lateinit var tvTimeRemainingSec: TextView
    private lateinit var pbTime: ProgressBar
    private lateinit var tvGoodOrBadAnswer: TextView
    private lateinit var tvScoreEvolution: TextView
    private lateinit var tvQuestionExplanation: TextView
    private lateinit var btnNextQuestion: Button
    private lateinit var tvQuizResultCurrentScore: TextView
    private lateinit var tvResultQuizGoodAnswers: TextView
    private lateinit var btnNewGame: Button
    private lateinit var btnScoreBoard: Button
    private lateinit var btnMainMenu: Button

    //Data
    private lateinit var quiz : Quiz

    //question index
    private var questionIndex = 0

    //time management
    private lateinit var timer : CountDownTimer
    private var timerState = TimerState.Stopped
    private var remainingSeconds = 0
    private var pbProgressValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_quiz)
        quiz = intent.getSerializableExtra("quiz") as Quiz
        initialConfiguration()
        initTimer()
        uiDisplayAQuestion(quiz.questions[0])
    }

    /** this method find views by id, set on click listeners and init initial content independently of question played.
     * It also stop the current timer **/
    private fun initialConfiguration() {
        //find views - layout
        layoutBtnAnswers = findViewById(R.id.layout_btnAnswers)
        layoutTimer = findViewById(R.id.layout_timer)
        layoutResultAnswer = findViewById(R.id.layout_resultAnswer)
        layoutQuizState = findViewById(R.id.layout_quizState)
        layoutResultQuiz = findViewById(R.id.layout_resultQuiz)
        //find views - title
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)
        //find views - quiz state
        tvQuizStateCurrentScore = findViewById(R.id.tv_QuizStateCurrentScore)
        tvQuestion = findViewById(R.id.tv_questionContent)
        tvQuestionNumber = findViewById(R.id.tv_questionNumber)
        tvQuizStateMaxQuestion = findViewById(R.id.tv_QuizStateMaxQuestions)
        btnAnswer1 = findViewById(R.id.btn_answer1)
        btnAnswer2 = findViewById(R.id.btn_answer2)
        btnAnswer3 = findViewById(R.id.btn_answer3)
        btnAnswer4 = findViewById(R.id.btn_answer4)
        //find views - timer
        tvTimeRemainingSec = findViewById(R.id.tv_timeRemainingSec)
        pbTime = findViewById(R.id.pb_timer)
        //find views - result answer
        tvGoodOrBadAnswer = findViewById(R.id.tv_goodOrBadAnswer)
        tvScoreEvolution = findViewById(R.id.tv_scoreEvolution)
        tvQuestionExplanation = findViewById(R.id.tv_questionExplanation)
        btnNextQuestion = findViewById(R.id.btn_nextQuestion)
        //find views - result quiz
        tvQuizResultCurrentScore = findViewById(R.id.tv_resultQuizCurrentScore)
        tvResultQuizGoodAnswers = findViewById(R.id.tv_resultQuizGoodAnswers)
        btnNewGame = findViewById(R.id.btn_resultQuizNewGame)
        btnScoreBoard = findViewById(R.id.btn_resultQuizScoreBoard)
        btnMainMenu = findViewById(R.id.btn_resultQuizMainMenu)

        //setListeners
        btnAnswer1.setOnClickListener(this)
        btnAnswer2.setOnClickListener(this)
        btnAnswer3.setOnClickListener(this)
        btnAnswer4.setOnClickListener(this)
        btnNextQuestion.setOnClickListener(this)
        btnNewGame.setOnClickListener(this)
        btnScoreBoard.setOnClickListener(this)
        btnMainMenu.setOnClickListener(this)

        //init content
        uiDisplayInitialContent()
    }

    private fun calculateScore(): Int {
        var currentMultiplier = 0

        when {
            quiz.params.levels[0].lvl_id.toInt() == 1 -> currentMultiplier = AppConst.scoreMultiplierEasy
            quiz.params.levels[0].lvl_id.toInt() == 2 -> currentMultiplier = AppConst.scoreMultiplierMedium
            quiz.params.levels[0].lvl_id.toInt() == 3 -> currentMultiplier = AppConst.scoreMultiplierConfirmed
        }
        val currentAnswerScore = currentMultiplier * (remainingSeconds)
        quiz.score += currentAnswerScore
        Log.v(AppConst.TAG_CONTROLLER, "Remaining sec: $remainingSeconds - current multiplier : $currentMultiplier --> score : $currentAnswerScore")
        return currentAnswerScore
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
            btnNextQuestion -> goToNextQuestion()
            btnNewGame -> goToSetUpQuiz()
            btnScoreBoard -> goToScoreBoard()
            btnMainMenu -> goToMainMenu()
        }
    }

    private fun verifyIfGoodAnswer(btnAnswerText: CharSequence) {
        val goodAnswer = quiz.questions[questionIndex].quest_answer1
        val isGoodAnswer: Boolean
        var currentAnswerScore = 0
        stopTimer()

        when (btnAnswerText) {
            goodAnswer -> {
                isGoodAnswer = true
                quiz.nbGoodAnswer++
                currentAnswerScore = calculateScore()
            }
            else -> isGoodAnswer = false
        }
        uiShowQuestionResult(isGoodAnswer, currentAnswerScore)
    }

    //change question when user click on this question
    private fun goToNextQuestion() {
        pbProgressValue = 0
        uiUpdateTimeProgressBar()

        if (questionIndex < quiz.questions.size - 1) {
            questionIndex++
            layoutBtnAnswers.visibility = VISIBLE
            layoutTimer.visibility = VISIBLE
            layoutResultAnswer.visibility = GONE
            uiDisplayAQuestion(quiz.questions[questionIndex])
        } else
            uiDisplayResultQuiz()
    }

    private  fun goToSetUpQuiz() {
        val intentSetUpQuizActivity = Intent(this, SetUpQuizActivity::class.java)
        startActivity(intentSetUpQuizActivity)
        finish()
    }

    private fun goToScoreBoard() {
        //todo: implement scoreBoard
    }

    private fun goToMainMenu() {
        val intentMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentMainActivity)
        finish()
    }


    /* *******************
    --Timer management--
    ********************* */

    /**
     * init timer object with a duration of quiz.time.toLong() * 1000 milliseconds  and intervals of 100 milliseconds...
     * onTick method add +1 to progress bar value & display remaining seconds + 1 sec (to avoid to display a 0 sec remaining time)
     * **/
    private fun initTimer() {
        timer = object : CountDownTimer(quiz.time.toLong() * 1000, 100) {
            override fun onFinish() {
                timerState = TimerState.Over
                uiShowQuestionResult(false, 0)
            }
            override fun onTick(millisUntilFinished: Long) {
                remainingSeconds = (millisUntilFinished / 1000 + 1).toInt()
                pbProgressValue++
                uiUpdateTimeProgressBar()
            }
        }
    }

    private fun launchTimer() {
        timer.start()
        timerState = TimerState.Running
    }

    private fun stopTimer() {
        timer.cancel()
        timerState = TimerState.Stopped
    }

    override fun onPause() {
        super.onPause()
        if(timerState == TimerState.Running) {
            timer.cancel()
            timerState = TimerState.Stopped
        }
    }

    /* *******************
         --Updates --
    ********************* */

    private fun uiDisplayInitialContent() {
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = quiz.params.themes[0].theme_name
        tvQuizStateCurrentScore.text = getString(R.string.score, 0)
        tvQuizStateMaxQuestion.text = getString(R.string.questionNumberMax, quiz.nbQuestions)
        remainingSeconds = quiz.time
        tvTimeRemainingSec.text = quiz.time.toString()
    }

    /** display the content of a question : the question itself, this number in question list and it's possible answers
     *
     * @param[question] this method take the question to display as param
     * **/
    private fun uiDisplayAQuestion(question: Question) {
        tvQuestion.text = question.quest_content
        tvQuestionNumber.text = (questionIndex + 1).toString()
        btnAnswer1.text = question.quest_answer1
        btnAnswer2.text = question.quest_answer2
        btnAnswer3.text = question.quest_answer3
        btnAnswer4.text = question.quest_answer4
        launchTimer()
    }

    /** Update UI with current remainingSeconds & progress value for pb */
    private fun uiUpdateTimeProgressBar() {
        pbTime.progress = pbProgressValue
        tvTimeRemainingSec.text = remainingSeconds.toString()
    }

    /** Update UI by hiding answerBtn & Timer and showing result contents
     * @param[isGoodAnswer] a boolean to indicate if user answer well or not
     * @param[currentAnswerScore] score gained by user when he responded well
     * If answer provided is wrong & timer is over, a specific message is displayed
     * **/
    private fun uiShowQuestionResult(isGoodAnswer: Boolean, currentAnswerScore: Int) {
        layoutBtnAnswers.visibility = GONE
        layoutTimer.visibility = GONE
        layoutResultAnswer.visibility = VISIBLE

        if(isGoodAnswer) {
            tvQuizStateCurrentScore.text = getString(R.string.score, quiz.score)
            tvGoodOrBadAnswer.text = getString(R.string.answerGood)
            tvScoreEvolution.text = getString(R.string.scoreEvolution, currentAnswerScore)
        } else {
            tvScoreEvolution.text = getString(R.string.scoreEvolution0)
            if(timerState == TimerState.Over)
                tvGoodOrBadAnswer.text = getString(R.string.answerNonExistent)
            else
                tvGoodOrBadAnswer.text = getString(R.string.answerBad)
        }
        tvQuestionExplanation.text = quiz.questions[questionIndex].quest_explanation
    }

    private fun uiDisplayResultQuiz() {
        layoutQuizState.visibility = GONE
        layoutResultAnswer.visibility = GONE
        layoutResultQuiz.visibility = VISIBLE
        tvQuizResultCurrentScore.text = quiz.score.toString()
        tvResultQuizGoodAnswers.text = getString(R.string.answersNbGoodAnswers,quiz.nbGoodAnswer, quiz.nbQuestions)
        Log.w(AppConst.TAG_CONTROLLER, "Quiz is over!")
    }

    //todo: randomise answers btn
}
