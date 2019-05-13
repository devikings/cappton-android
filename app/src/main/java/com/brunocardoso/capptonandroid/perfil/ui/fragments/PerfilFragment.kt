package com.brunocardoso.capptonandroid.perfil.ui.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.perfil.presenter.PerfilPresenter
import com.brunocardoso.capptonandroid.perfil.view.PerfilFragmentCallback
import com.brunocardoso.capptonandroid.perfil.view.PerfilView
import com.brunocardoso.capptonandroid.schedule.view.LogoutUserCallback
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.view.UserFragmentCallback
import kotlinx.android.synthetic.main.frag_perfil.*

class PerfilFragment : Fragment() {

    private var callback: LogoutUserCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = UserPresenter.getUser()
        tv_perfil_user.text = user?.displayName
        tv_perfil_email.text = user?.email

        btn_logout.setOnClickListener {
            callback?.onLogout()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            callback = context as LogoutUserCallback
        }catch (e: ClassCastException){
            throw ClassCastException("$callback must implement ${LogoutUserCallback::class.java.simpleName}")
        }
    }

}
