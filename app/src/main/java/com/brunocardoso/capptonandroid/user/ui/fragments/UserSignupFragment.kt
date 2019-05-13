package com.brunocardoso.capptonandroid.user.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.utils.AlertDialogCustomerUtil
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.view.UserView
import com.brunocardoso.capptonandroid.user.view.UserFragmentCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.frag_user_signup.*
import java.lang.ClassCastException

class UserSignupFragment : Fragment(), UserView {

    private lateinit var presenter: UserPresenter
    private var callback: UserFragmentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_user_signup, container, false)
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

            if( name.equals("") || email.equals("") || passw.equals("")) {

                val snackBar = Snackbar.make(btn_signup, "Erro at signup, try again! ", Snackbar.LENGTH_LONG)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.view.setBackgroundColor(Color.RED)
                snackBar.show()

            }else {

                presenter.signup(name, email, passw)
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
            callback = context as UserFragmentCallback
        }catch (e: ClassCastException){
            throw ClassCastException("$callback must implement ${UserFragmentCallback::class.java.simpleName}")
        }
    }

    override fun onSuccess() {

        val snackBar = Snackbar.make(btn_signup, "Signup with success, now you signin", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.view.setBackgroundColor(Color.GREEN)
        snackBar.show()

        callback?.onSignupSuccessful()
    }

    override fun onError(error: String) {
        AlertDialogCustomerUtil.createDialog(requireContext(), "Error at app", error)
    }
}
