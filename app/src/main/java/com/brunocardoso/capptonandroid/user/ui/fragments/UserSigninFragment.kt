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
import com.brunocardoso.capptonandroid.infra.utils.snackbarBuilder
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.view.UserView
import com.brunocardoso.capptonandroid.user.view.UserFragmentCallback
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

            if((email.equals("")) && (passw.equals(""))){
                snackbarBuilder(btn_signin, "Please fill in the fields and try again!", Color.WHITE, Color.RED)
            }else if(email.equals("")){
                snackbarBuilder(btn_signin, "Please fill in the email field and try again!", Color.WHITE, Color.RED)
            }else if(passw.equals("")){
                snackbarBuilder(btn_signin, "Please fill in the password field and try again!", Color.WHITE, Color.RED)
            } else {

                snackbarBuilder(btn_signin, "Authenticating, wait!", Color.BLACK, Color.GREEN)
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
        snackbarBuilder(btn_signin, error, Color.WHITE, Color.RED)
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
