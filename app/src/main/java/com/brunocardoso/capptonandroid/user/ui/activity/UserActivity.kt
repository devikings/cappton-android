package com.brunocardoso.capptonandroid.user.ui.activity

import android.os.Bundle
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.schedule.ui.activity.ScheduleActivity
import com.brunocardoso.capptonandroid.user.ui.fragments.SigninFragment
import com.brunocardoso.capptonandroid.user.ui.fragments.SignupFragment
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
            openActivity(ScheduleActivity::class, null)
            finish()
        }

        replaceFragmentOnContainer( R.id.frame_container, SigninFragment() )
    }

    /*
     * Signin sucessful
     * Open activity pautas
     */
    override fun onSucessful() {
        openActivity(ScheduleActivity::class, null)
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
        replaceFragmentOnContainer( R.id.frame_container, SignupFragment() )
    }

    /*
     * Verify if user is authenticated
     */
    fun verifyAuthenticatedUser(): Boolean{
        if (FirebaseAuth.getInstance().currentUser == null) return false
        return true
    }
}
