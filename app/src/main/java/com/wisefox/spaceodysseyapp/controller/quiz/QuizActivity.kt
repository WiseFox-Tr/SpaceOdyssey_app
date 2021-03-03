package com.wisefox.spaceodysseyapp.controller.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.CommonController
import com.wisefox.spaceodysseyapp.model.webServices.WebServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class QuizActivity : AppCompatActivity() {

    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var rootView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        findViewsAndInitContent()
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)
        rootView = findViewById(R.id.root_quiz)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = getString(R.string.whatWantToDo)
    }

    fun onClickSetUpQuiz(view: View) {
        //todo: add progress bar
        CoroutineScope(IO).launch {
            try {
                //request for params
                val paramsRetrieved = WebServices.getParams()

                val intentSetUpQuizActivity = Intent(this@QuizActivity, SetUpQuizActivity::class.java)
                intentSetUpQuizActivity.putExtra("params", paramsRetrieved)
                startActivity(intentSetUpQuizActivity)
                finish()
            }
            catch (e: Exception) {
                e.printStackTrace()
                launch(Main) {
                    CommonController.displaySnackbar(
                            rootView = rootView,
                            message = CommonController.manageError(exception = e, context = this@QuizActivity),
                            context = this@QuizActivity
                    )
                }
            }
        }
    }
}
