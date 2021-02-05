package com.wisefox.spaceodysseyapp.controller.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.model.LevelBean
import com.wisefox.spaceodysseyapp.model.ParamsBean
import com.wisefox.spaceodysseyapp.model.ThemeBean
import com.wisefox.spaceodysseyapp.model.webServices.WSUtils
import com.wisefox.spaceodysseyapp.utils.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.Exception

class SetUpQuizActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView

    //data
    private var params = ParamsBean(
        levelBean = LevelBean(1, "Débutant"),
        themeBean = ThemeBean(1, "Systèmes planétaires")
    )

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
            //request for questions & intent PlayQuizActivity
            try {
                val questionList = WSUtils.getQuestions(params)
                Log.d(Const.TAG_CONTROLLER, "List of questions retrieved : --> $questionList")
                //after intent new activity
                val intentPlayQuizActivity = Intent(this@SetUpQuizActivity, PlayQuizActivity::class.java)
                startActivity(intentPlayQuizActivity)
            }
            catch (e :Exception) {
                e.printStackTrace()
                //todo: update UI by displaying an error message
            }
        }
    }
}