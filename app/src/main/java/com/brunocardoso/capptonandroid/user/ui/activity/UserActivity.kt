package com.brunocardoso.capptonandroid.user.ui.activity

import android.graphics.Color
import android.os.Bundle
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.infra.utils.snackbarBuilder
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import com.brunocardoso.capptonandroid.schedule.ui.activity.ScheduleActivity
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.ui.fragments.UserResetPasswFragment
import com.brunocardoso.capptonandroid.user.ui.fragments.UserSigninFragment
import com.brunocardoso.capptonandroid.user.ui.fragments.UserSignupFragment
import com.brunocardoso.capptonandroid.user.view.UserFragmentCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.frag_user_reset_passw.*
import kotlinx.android.synthetic.main.frag_user_signup.*

class UserActivity : BaseActivity(), UserFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)
    }

    override fun onStart() {
        super.onStart()

        // verify if user is not authenticated
        if (UserPresenter.verifyUserAuthenticated()) {


            openActivity(ScheduleActivity::class, null)
            finish()
        }

        replaceFragmentOnContainer( R.id.frame_container, UserSigninFragment() )

    }

    override fun openSignupView() {
        replaceFragmentOnContainer(R.id.frame_container, UserSignupFragment())
    }

    override fun openResetPasswordView() {
        replaceFragmentOnContainer(R.id.frame_container, UserResetPasswFragment())
    }

    override fun onSigninSuccessful() {

        snackbarBuilder(btn_reset_passw, "Signin with successful!", Color.BLACK, Color.GREEN)

        openActivity(ScheduleActivity::class)
        finish()

    }

    override fun onSignupSuccessful() {

        replaceFragmentOnContainer(R.id.frame_container, UserSigninFragment())
    }

    override fun onResetPasswordSuccessful() {
        replaceFragmentOnContainer(R.id.frame_container, UserSigninFragment())
    }
}
