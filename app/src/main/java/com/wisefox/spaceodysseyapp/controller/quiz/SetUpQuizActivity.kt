package com.wisefox.spaceodysseyapp.controller.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.CommonController
import com.wisefox.spaceodysseyapp.model.*
import com.wisefox.spaceodysseyapp.model.webServices.WebServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class SetUpQuizActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var rootView: View

    //data
    private var params = Params(
            level = Level(1, "Débutant"),
            theme = Theme(1, "Systèmes planétaires")
    )
    private lateinit var quiz :Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up_quiz)
        findViewsAndInitContent()
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_sub_title)
        rootView = findViewById(R.id.root_set_up)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = ""
    }

    fun onClickPlayQuiz(view: View) {

        CoroutineScope(IO).launch {
            //request for questions & start PlayQuizActivity with questionsList retrieved from server
            try {
                val questionsList = WebServices.getQuestions(params)
                quiz = Quiz(questionsList, params = params)

                val intentPlayQuizActivity = Intent(this@SetUpQuizActivity, PlayQuizActivity::class.java)
                intentPlayQuizActivity.putExtra("quiz", quiz)

                startActivity(intentPlayQuizActivity)
            }
            catch (e: Exception) {
                e.printStackTrace()
                launch(Main) {
                    CommonController.displaySnackbar(
                            rootView = rootView,
                            message = CommonController.manageError(exception = e, context = this@SetUpQuizActivity),
                            context = this@SetUpQuizActivity
                    )
                }
            }
        }
    }
}