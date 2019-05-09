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
import kotlinx.android.synthetic.main.frag_signup.*
import java.lang.ClassCastException

class SignupFragment : Fragment(), UserView {

    private lateinit var presenter: UserPresenter
    private var callback: UserViewCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_signup, container, false)
    }

    /*
     * View Created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = UserPresenter(requireContext(), this)

        /*
         * Click button signup
         */
        btn_signup.setOnClickListener({
            val name = edt_name.text.toString()
            val email = edt_email.text.toString()
            val passw = edt_passw.text.toString()

            presenter.signup(name, email, passw)
        })

    }

    /*
     * Signup succeful
     */
    override fun onSuccess() {
        callback?.onSucessful()
    }

    /*
     * Error at signup
     */
    override fun onError(error: String) {
        callback?.onError()
    }

    override fun onLogout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
            callback = context as UserViewCallback
        }catch (e: ClassCastException){
            throw ClassCastException("$callback must implement ${UserViewCallback::class.java.simpleName}")
        }
    }
}
