package com.brunocardoso.capptonandroid.user.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.view.UserView
import com.brunocardoso.capptonandroid.user.view.UserViewCallback
import kotlinx.android.synthetic.main.frag_signin.*


class SigninFragment : Fragment(), UserView {

    private lateinit var presenter: UserPresenter
    private var callback: UserViewCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_signin, container, false)
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
//            val email: String = view.findViewById<EditText>(R.id.edt_email).text.toString()
//            val passw: String = view.findViewById<EditText>(R.id.edt_passw).text.toString()
//
//            presenter.signin(email, passw)
        })

        // click text register for open fragment SignupFragment
        tv_open_signup.setOnClickListener({
            callback?.openSignupView()
        })
    }

    /*
     * Signin succeful
     */
    override fun onSuccess() {
        callback?.onSucessful()
    }

    /*
     * Error at signin
     */
    override fun onError(error: String) {
        callback?.onError()
    }

    /*
     * Logout user and
     */
    override fun onLogout() {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            callback = context as UserViewCallback
        }catch (e: ClassCastException){
            throw ClassCastException("$callback must implement ${UserViewCallback::class.java.simpleName}")
        }
    }

}
