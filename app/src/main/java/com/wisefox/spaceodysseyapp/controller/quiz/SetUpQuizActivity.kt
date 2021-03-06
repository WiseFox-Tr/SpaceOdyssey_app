package com.wisefox.spaceodysseyapp.controller.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.CommonController
import com.wisefox.spaceodysseyapp.model.*
import com.wisefox.spaceodysseyapp.model.webServices.WebServices
import com.wisefox.spaceodysseyapp.view.CustomGraphicComponents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class SetUpQuizActivity : AppCompatActivity() {

    private lateinit var appbar: androidx.appcompat.widget.Toolbar
    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var rootView: View
    private lateinit var pbLoad: ProgressBar
    private lateinit var spinnerTheme: Spinner
    private lateinit var spinnerLevel: Spinner

    //data
    private lateinit var paramsRetrieved: Params
    private lateinit var params: Params
    private var levelChosen = Level(0, "")
    private var themeChosen = Theme(0, "")
    private lateinit var quiz :Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up_quiz)
        paramsRetrieved = intent.getSerializableExtra("params") as Params
        //todo: trouver une solution lorsque l'utilisateur cherche à relancer une nouvelle partie
        findViewsAndInitContent()
        setSupportActionBar(appbar)
        fillSpinner()
        spinnersListeners()
    }

    private fun findViewsAndInitContent() {
        //find views
        appbar = findViewById(R.id.appBar)
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)
        rootView = findViewById(R.id.root_setUp)
        pbLoad = findViewById(R.id.pb_setUpQuizLoadPB)
        spinnerTheme = findViewById(R.id.spinner_theme)
        spinnerLevel = findViewById(R.id.spinner_level)
        //init content
        tvTitle.text = getString(R.string.quiz)
        tvSubTitle.text = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        CommonController.itemSelectedCallback(item, this)
        return super.onOptionsItemSelected(item)
    }

    /** it puts into ArrayList<String> theme & level names and use ArrayAdapter to fill spinners **/
    private fun fillSpinner() {
        val themeList = ArrayList<String>()
        val levelList = ArrayList<String>()
        paramsRetrieved.themes.forEach { themeList.add(it.theme_name) }
        paramsRetrieved.levels.forEach { levelList.add(it.lvl_name) }
        spinnerTheme.adapter = CustomGraphicComponents.setUpSpinnerAdapter(context = this, data = themeList)
        spinnerLevel.adapter = CustomGraphicComponents.setUpSpinnerAdapter(context = this, data = levelList)
    }

    private fun spinnersListeners() {
        spinnerTheme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                themeChosen = paramsRetrieved.themes[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerLevel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                levelChosen = paramsRetrieved.levels[position]
                Log.d(AppConst.TAG_CONTROLLER, "Level chosen : $levelChosen")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun onClickPlayQuiz(view: View) {
        pbLoad.visibility = VISIBLE
        CoroutineScope(IO).launch {
            //request for questions & start PlayQuizActivity with questionsList retrieved from server
            try {
                params = Params(mutableListOf(levelChosen), mutableListOf(themeChosen))
                val questionsList = WebServices.getQuestions(params)
                quiz = Quiz(questionsList, params = params)

                val intentPlayQuizActivity = Intent(this@SetUpQuizActivity, PlayQuizActivity::class.java)
                intentPlayQuizActivity.putExtra("quiz", quiz)
                intentPlayQuizActivity.putExtra("params", paramsRetrieved)
                startActivity(intentPlayQuizActivity)
                finish()
            }
            catch (e: Exception) {
                e.printStackTrace()
                launch(Main) {
                    pbLoad.visibility = INVISIBLE
                    CommonController.displaySnackbar(
                            rootView = rootView,
                            message = CommonController.manageError(exception = e, context = this@SetUpQuizActivity)
                    )
                }
            }
        }
    }
}