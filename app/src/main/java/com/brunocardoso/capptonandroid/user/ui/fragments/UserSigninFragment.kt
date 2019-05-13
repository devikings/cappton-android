package com.brunocardoso.capptonandroid.user.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.utils.AlertDialogCustomerUtil
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.view.UserView
import com.brunocardoso.capptonandroid.user.view.UserFragmentCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.frag_user_signin.*


class UserSigninFragment : Fragment(), UserView {

    private lateinit var presenter: UserPresenter
    private var callback: UserFragmentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_user_signin, container, false)
    }

    /*
     * View Created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initial presenter
        presenter = UserPresenter(requireContext(), this)

        // click button signin
        btn_signin.setOnClickListener({
            val email: String = edt_email.text.toString()
            val passw: String = edt_passw.text.toString()

            if((email.equals("")) || (passw.equals(""))){

                val snackBar = Snackbar.make(btn_signin, "Failed auth, try again! ", Snackbar.LENGTH_LONG)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.view.setBackgroundColor(Color.RED)
                snackBar.show()

            }else {

                presenter.signin(email, passw)
            }
        })

        // click text register for open fragment SignupFragment
        tv_open_signup.setOnClickListener({
            callback?.openSignupView()
        })

        tv_open_reset_passw.setOnClickListener{
            callback?.openResetPasswordView()
        }
    }

    /*
     * Signin succeful
     */
    override fun onSuccess() {
        callback?.onSigninSuccessful()
    }

    /*
     * Error at signin
     */
    override fun onError(error: String) {
        AlertDialogCustomerUtil.createDialog(requireContext(), "Error at app", error)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            callback = context as UserFragmentCallback
        }catch (e: ClassCastException){
            throw ClassCastException("$callback must implement ${UserFragmentCallback::class.java.simpleName}")
        }
    }

}
