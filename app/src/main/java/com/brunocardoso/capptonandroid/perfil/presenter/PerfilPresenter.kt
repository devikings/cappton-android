package com.brunocardoso.capptonandroid.perfil.presenter

import android.content.Context
import com.brunocardoso.capptonandroid.perfil.view.PerfilView

class PerfilPresenter(
    val context: Context,
    val callback: PerfilView? = null
) {
    fun create(){ callback?.onSuccess() }
    fun delete(id: Int){ callback?.onSuccess() }
    fun update(id: Int){ callback?.onSuccess() }
}