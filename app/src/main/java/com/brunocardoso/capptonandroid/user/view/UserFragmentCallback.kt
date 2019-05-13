package com.brunocardoso.capptonandroid.user.view

interface UserFragmentCallback {
    fun openSignupView()
    fun openResetPasswordView()
    fun onSigninSuccessful()
    fun onSignupSuccessful()
    fun onResetPasswordSuccessful()
}