package com.wisefox.spaceodysseyapp.view

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.wisefox.spaceodysseyapp.R

class CustomGraphicComponents {
    companion object {
        fun setSnackbarStyle(
                snackbar: Snackbar,
                backgroundTintColor: Int = snackbar.context.getColor(R.color.text_red),
                textColor: Int = snackbar.context.getColor(R.color.white),
                textSize: Float = 14f,
                minHeight : Int = 300,
                textAlignment: Int = TextView.TEXT_ALIGNMENT_CENTER
        ) {
            //set colors properties
            snackbar.setTextColor(textColor)
            snackbar.setBackgroundTint(backgroundTintColor)

            //set text properties
            val snackbarText = snackbar.view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
            snackbarText.textSize = textSize
            snackbarText.textAlignment = textAlignment
            snackbarText.setTypeface(snackbarText.typeface, Typeface.BOLD)

            //set height properties
            val snackbarView = snackbar.view
            snackbarView.minimumHeight = minHeight
        }

        fun setUpSpinnerAdapter(context: Context, data: ArrayList<String>): ArrayAdapter<*> {
            val adapter: ArrayAdapter<*> = ArrayAdapter(context, R.layout.spinner_item, data)
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            return adapter
        }

        fun setTextInputStyle(context: Context, textInputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
            textInputLayout.hintTextColor = context.getColorStateList(R.color.text_yellow_orange)
            textInputLayout.defaultHintTextColor = context.getColorStateList(R.color.text_yellow_orange)
            textInputLayout.boxBackgroundColor = context.getColor(R.color.action_purple)
            textInputLayout.boxStrokeColor = context.getColor(R.color.btn_yellow_orange)
            textInputLayout.setErrorTextColor(context.getColorStateList(R.color.text_red))
            textInputLayout.boxStrokeErrorColor = context.getColorStateList(R.color.text_red)
            textInputLayout.counterTextColor = context.getColorStateList(R.color.text_white)
            textInputLayout.counterOverflowTextColor = context.getColorStateList(R.color.text_red)
            textInputEditText.setTextColor(context.getColor(R.color.text_white))
        }
    }
}