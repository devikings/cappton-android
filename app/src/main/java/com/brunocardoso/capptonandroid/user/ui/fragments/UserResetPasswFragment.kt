package com.brunocardoso.capptonandroid.user.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.utils.snackbarBuilder
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.view.UserView
import com.brunocardoso.capptonandroid.user.view.UserFragmentCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.frag_user_reset_passw.*
import java.lang.ClassCastException

class UserResetPasswFragment : Fragment(), UserView {

    private lateinit var presenter: UserPresenter
    private var callback: UserFragmentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_user_reset_passw, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = UserPresenter(requireContext(), this)

        btn_reset_passw.setOnClickListener{
            val email = edt_email.text.toString()

            if (email.equals("")){

                snackbarBuilder(btn_reset_passw, "It email not exists, try again!", Color.WHITE, Color.RED)

            }else {

                snackbarBuilder(btn_reset_passw, "Please, verify your email!", Color.WHITE, Color.RED)

                presenter.resetPassword(email)
            }
        }
    }

    override fun onSuccess() {
        callback?.onResetPasswordSuccessful()
    }

    override fun onError(error: String) {
        snackbarBuilder(btn_reset_passw, "Error at app!", Color.WHITE, Color.RED)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
            callback = context as UserFragmentCallback
        }catch (e: ClassCastException){
            throw ClassCastException("$callback must implement ${UserFragmentCallback::class.java.simpleName}")
        }
    }
}
