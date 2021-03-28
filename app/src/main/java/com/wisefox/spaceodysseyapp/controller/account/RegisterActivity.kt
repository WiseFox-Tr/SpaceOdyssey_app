package com.wisefox.spaceodysseyapp.controller.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.wisefox.spaceodysseyapp.R
import com.wisefox.spaceodysseyapp.controller.CommonController
import com.wisefox.spaceodysseyapp.model.AppConst
import com.wisefox.spaceodysseyapp.utils.AuthServices
import com.wisefox.spaceodysseyapp.view.CustomGraphicComponents
import java.lang.Exception

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rootView : View
    private lateinit var appbar: androidx.appcompat.widget.Toolbar
    private lateinit var title: TextView
    private lateinit var subTitle: TextView
    private lateinit var tiRegisterPseudo: TextInputLayout
    private lateinit var etRegisterPseudo: TextInputEditText
    private lateinit var tiRegisterMail: TextInputLayout
    private lateinit var etRegisterMail: TextInputEditText
    private lateinit var tiRegisterPassword: TextInputLayout
    private lateinit var etRegisterPassword: TextInputEditText
    private lateinit var tiRegisterPasswordConfirmation: TextInputLayout
    private lateinit var etRegisterPasswordConfirmation: TextInputEditText
    private lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewsAndInitContent()
        setSupportActionBar(appbar)
    }

    private fun findViewsAndInitContent() {
        //find Views
        rootView = findViewById(R.id.root_register)
        appbar = findViewById(R.id.appBar)
        title = findViewById(R.id.tv_title)
        subTitle = findViewById(R.id.tv_subTitle)
        tiRegisterPseudo = findViewById(R.id.ti_registerPseudo)
        etRegisterPseudo = findViewById(R.id.et_registerPseudo)
        tiRegisterMail = findViewById(R.id.ti_registerMail)
        etRegisterMail = findViewById(R.id.et_registerMail)
        tiRegisterPassword = findViewById(R.id.ti_registerPassword)
        etRegisterPassword= findViewById(R.id.et_registerPassword)
        tiRegisterPasswordConfirmation = findViewById(R.id.ti_registerPasswordConfirmation)
        etRegisterPasswordConfirmation = findViewById(R.id.et_registerPasswordConfirmation)
        btnRegister = findViewById(R.id.btn_register)

        //setUp title
        title.text = getString(R.string.myIds)
        subTitle.text = ""

        //setUp inputText style
        CustomGraphicComponents.setTextInputStyle(context = this, tiRegisterPseudo, etRegisterPseudo)
        CustomGraphicComponents.setTextInputStyle(context = this, tiRegisterMail, etRegisterMail)
        CustomGraphicComponents.setTextInputStyle(context = this, tiRegisterPassword, etRegisterPassword)
        CustomGraphicComponents.setTextInputStyle(context = this, tiRegisterPasswordConfirmation, etRegisterPasswordConfirmation)

        //set click listeners
        btnRegister.setOnClickListener(this)
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
            btnRegister -> {
                val pseudoInput = etRegisterPseudo.text.toString().trim()
                val mailInput = etRegisterMail.text.toString().trim()
                val passwordInput = etRegisterPassword.text.toString()
                val passwordConfirmationInput = etRegisterPasswordConfirmation.text.toString()

                try {
                    AuthServices.checkLengthContentEditText(pseudoInput, AppConst.maxPseudoLength)
                    AuthServices.checkLengthContentEditText(mailInput, AppConst.maxMailLength)
                    AuthServices.checkLengthContentEditText(passwordInput, AppConst.maxPasswordLength)
                    AuthServices.checkLengthContentEditText(passwordConfirmationInput, AppConst.maxPasswordLength)
                    AuthServices.checkMailFormat(mailInput)
                    AuthServices.checkPasswordFormat(passwordInput)
                    AuthServices.checkCorrespondenceBetweenPasswords(passwordInput, passwordConfirmationInput)
                    //todo : call WebServices method to try to register (on secondary thread)

                    //finish activity
                    Toast.makeText(this, "Try to Register", Toast.LENGTH_SHORT).show()
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    CommonController.displaySnackbar(
                            rootView = rootView,
                            message = CommonController.manageError(exception = e, context = this@RegisterActivity)
                    )
                }
            }
        }
    }
}
