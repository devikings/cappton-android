package com.brunocardoso.capptonandroid.infra.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

class AlertDialogCustomerUtil {
    companion object {
       fun createDialog(context: Context,
                        title: String,
                        mensagem: String) {

           val builder = AlertDialog.Builder(context)
           builder.setTitle(title)
           builder.setMessage(mensagem)
           builder.setCancelable(true)
           builder.create()
               .show()

       }
    }
}