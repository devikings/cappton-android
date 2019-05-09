package com.brunocardoso.capptonandroid.user.activity

import android.os.Bundle
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.pauta.activity.PautasActivity
import com.brunocardoso.capptonandroid.user.fragments.SigninFragment
import com.brunocardoso.capptonandroid.user.fragments.SignupFragment
import com.brunocardoso.capptonandroid.user.view.UserViewCallback
import com.google.firebase.auth.FirebaseAuth

class UserActivity : BaseActivity(), UserViewCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)
    }

    override fun onStart() {
        super.onStart()

        // verify if user is not authenticated
        if (verifyAuthenticatedUser()) {
            openActivity(PautasActivity::class, null)
            finish()
        }

        replaceFragmentOnContainer( SigninFragment() )
    }

    /*
     * Signin sucessful
     * Open activity pautas
     */
    override fun onSucessful() {
        openActivity(PautasActivity::class, null)
        finish()
    }

    /*
     * Erros at signin
     * Show menssage on Toast
     */
    override fun onError() {
        // Exibe a mensagem no toast
    }

    /*
     * Event of click to register user
     */
    override fun openSignupView() {
        replaceFragmentOnContainer( SignupFragment() )
    }

    /*
     * Verify if user is authenticated
     */
    fun verifyAuthenticatedUser(): Boolean{
        if (FirebaseAuth.getInstance().currentUser == null) return false
        return true
    }
}
