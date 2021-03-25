package com.wisefox.spaceodysseyapp.controller.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.CommonController
import com.wisefox.spaceodysseyapp.view.CustomGraphicComponents

class LogInActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var appbar: androidx.appcompat.widget.Toolbar
    private lateinit var tiLogInPseudo: TextInputLayout
    private lateinit var etLogInPseudo: TextInputEditText
    private lateinit var tiLogInPassword: TextInputLayout
    private lateinit var etLogInPassword: TextInputEditText
    private lateinit var btnGoToRegisterScreen: Button
    private lateinit var btnLogIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        findViewsAndInitContent()
        setSupportActionBar(appbar)
    }

    private fun findViewsAndInitContent() {
        //find views
        appbar = findViewById(R.id.appBar)
        tiLogInPseudo = findViewById(R.id.ti_logInPseudo)
        etLogInPseudo = findViewById(R.id.et_logInPseudo)
        tiLogInPassword = findViewById(R.id.ti_logInPassword)
        etLogInPassword = findViewById(R.id.et_logInPassword)
        btnGoToRegisterScreen = findViewById(R.id.btn_goToRegisterScreen)
        btnLogIn = findViewById(R.id.btn_logIn)

        //setUp inputText style
        CustomGraphicComponents.setTextInputStyle(context = this, tiLogInPseudo, etLogInPseudo)
        CustomGraphicComponents.setTextInputStyle(context = this, tiLogInPassword, etLogInPassword)

        //set click listeners
        btnLogIn.setOnClickListener(this)
        btnGoToRegisterScreen.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        CommonController.itemSelectedCallback(item, this)
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v) {
            btnLogIn -> { Toast.makeText(this, "Try to Log In", Toast.LENGTH_SHORT).show() }
            btnGoToRegisterScreen -> { Toast.makeText(this, "Want to go to register screen !", Toast.LENGTH_SHORT).show() }
        }
    }


}