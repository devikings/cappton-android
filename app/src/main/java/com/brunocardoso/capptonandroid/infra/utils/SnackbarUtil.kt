package com.brunocardoso.capptonandroid.infra.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun snackbarBuilder(view: View, text: String, textColor: Int, bgColor: Int){
    val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
    snackBar.setActionTextColor(textColor)
    snackBar.view.setBackgroundColor(bgColor)
    snackBar.show()
}