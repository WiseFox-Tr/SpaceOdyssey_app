package com.wisefox.spaceodysseyapp.controller.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.view.CustomGraphicComponents

class LogInActivity : AppCompatActivity() {

    private lateinit var tiLogInPseudo: TextInputLayout
    private lateinit var etLogInPseudo: TextInputEditText
    private lateinit var tiLogInPassword: TextInputLayout
    private lateinit var etLogInPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        findViewsAndInitContent()
    }

    private fun findViewsAndInitContent() {
        //find views
        tiLogInPseudo = findViewById(R.id.ti_logInPseudo)
        etLogInPseudo = findViewById(R.id.et_logInPseudo)
        tiLogInPassword = findViewById(R.id.ti_logInPassword)
        etLogInPassword = findViewById(R.id.et_logInPassword)

        //setUp inputText style
        CustomGraphicComponents.setTextInputStyle(context = this, tiLogInPseudo, etLogInPseudo)
        CustomGraphicComponents.setTextInputStyle(context = this, tiLogInPassword, etLogInPassword)
    }
}