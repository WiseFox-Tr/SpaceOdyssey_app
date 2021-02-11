package com.wisefox.spaceodysseyapp.controller.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.model.*
import com.wisefox.spaceodysseyapp.model.webServices.WSUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SetUpQuizActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView

    //data
    private var params = ParamsBean(
        level = LevelBean(1, "Débutant"),
        theme = ThemeBean(1, "Systèmes planétaires")
    )
    private lateinit var quiz :QuizBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up_quiz)
        findViewsAndInitContent()
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_sub_title)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = ""
    }

    fun onClickPlayQuiz(view: View) {

        CoroutineScope(IO).launch {
            //request for questions & start PlayQuizActivity with questionsList
            try {
                quiz = QuizBean(questions = WSUtils.getQuestions(params), params = params)

                val intentPlayQuizActivity = Intent(this@SetUpQuizActivity, PlayQuizActivity::class.java)
                intentPlayQuizActivity.putExtra("quiz", quiz)

                startActivity(intentPlayQuizActivity)
            }
            catch (e :Exception) {
                e.printStackTrace()
                //todo: update UI by displaying an error message
            }
        }
    }
}