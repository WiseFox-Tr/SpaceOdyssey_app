package com.wisefox.spaceodysseyapp.controller.quiz

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.CommonController
import com.wisefox.spaceodysseyapp.model.webServices.WebServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class QuizActivity : AppCompatActivity() {

    private lateinit var appbar: androidx.appcompat.widget.Toolbar
    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var rootView: View
    private lateinit var pbLoad: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        findViewsAndInitContent()
        setSupportActionBar(appbar)
    }

    private fun findViewsAndInitContent() {
        //find views
        appbar = findViewById(R.id.appBar)
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)
        rootView = findViewById(R.id.root_quiz)
        pbLoad = findViewById(R.id.pb_quizLoadPB)

        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = getString(R.string.whatWantToDo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        CommonController.itemSelectedCallback(item, this)
        return super.onOptionsItemSelected(item)
    }

    fun onClickSetUpQuiz(view: View) {
        pbLoad.visibility = VISIBLE
        CoroutineScope(IO).launch {
            try {
                //request for params
                val paramsRetrieved = WebServices.getParams()
                //set up intent, start new activity & finish this activity
                val intentSetUpQuizActivity = Intent(this@QuizActivity, SetUpQuizActivity::class.java)
                intentSetUpQuizActivity.putExtra("params", paramsRetrieved)
                startActivity(intentSetUpQuizActivity)
                finish()
            }
            catch (e: Exception) {
                e.printStackTrace()
                launch(Main) {
                    pbLoad.visibility = INVISIBLE
                    CommonController.displaySnackbar(
                            rootView = rootView,
                            message = CommonController.manageError(exception = e, context = this@QuizActivity)
                    )
                }
            }
        }
    }

    fun onClickScoreBoardActivity(view: View) { Toast.makeText(this, "ScoreBoard Clicked", Toast.LENGTH_SHORT).show() }
}
