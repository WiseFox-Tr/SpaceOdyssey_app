package com.wisefox.spaceodysseyapp.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.quiz.QuizActivity


class MainActivity : AppCompatActivity() {

    private lateinit var appbar: androidx.appcompat.widget.Toolbar
    // Graphics
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewsAndInitContent()
        setSupportActionBar(appbar)
    }

    private fun findViewsAndInitContent() {
        //find views
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)
        appbar = findViewById(R.id.appBar)

        //init Content
        tvTitle.text = getString(R.string.welcome)
        tvSubTitle.text = getString(R.string.whatToDo)
    }

    fun onClickQuizActivity(view: View) {
        val intentQuizActivity = Intent(this, QuizActivity::class.java)
        startActivity(intentQuizActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        CommonController.itemSelectedCallback(item, this)
        return super.onOptionsItemSelected(item)
    }

    fun onCLickNewsActivity(view: View) { Toast.makeText(this, "News Clicked", Toast.LENGTH_SHORT).show() }
    fun onClickLessonActivity(view: View) { Toast.makeText(this, "Lesson Clicked", Toast.LENGTH_SHORT).show() }
    fun onClickLogInActivity(view: View) { Toast.makeText(this, "LogIn Clicked", Toast.LENGTH_SHORT).show() }
}
