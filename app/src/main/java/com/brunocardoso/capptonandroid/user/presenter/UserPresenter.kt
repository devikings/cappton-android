package com.brunocardoso.capptonandroid.user.presenter

import android.content.Context
import android.widget.Toast
import com.brunocardoso.capptonandroid.user.view.UserView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class UserPresenter(
    private var context: Context,
    private var view: UserView
) {
    // Regras de negocio do userProfile
    // signin 1/
    // signup 1/
    // logout 1/
    // get 1/
    // update 1/
    // reset passw 1/

    fun signin(email: String, passw: String){
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, passw)
            .addOnCompleteListener {
                if (it.isSuccessful){

                    view.onSuccess()

                }else{
                    view.onError(it.exception?.message.toString())
                }
            }
    }

    fun signup(fullName: String, email: String, passw: String){
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, passw)
            .addOnCompleteListener({
                if (it.isSuccessful){

                    // Update data userprofile
                    updateUser(fullName)

                    // Logout user
                    logout()

                    // Notify view
                    view.onSuccess()

                }else{
                    view.onError(it.exception?.message.toString())
                }
            })
    }

    fun resetPassword(email: String){
        FirebaseAuth
            .getInstance()
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view.onSuccess()
                    Toast.makeText(context, "Email sent with success, verify your email",
                        Toast.LENGTH_SHORT).show()
                }else{

                    view.onError(task.exception?.message.toString())
                }
            }

    }

    companion object {
        /*
         * Logout user profile
         */
        fun logout() {
            FirebaseAuth
                .getInstance()
                .signOut()
        }

        /*
         * get user profile
         */
        fun getUser(): FirebaseUser? {
            return FirebaseAuth.getInstance().currentUser
        }

        /*
         * Verify if user is authenticated
         */
        fun verifyUserAuthenticated(): Boolean{
            if (FirebaseAuth.getInstance().currentUser == null) return false
            return true
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth
            .getInstance()
            .currentUser
    }

    fun updateUser(fullName: String){
        FirebaseAuth
            .getInstance()
            .currentUser?.updateProfile(
            UserProfileChangeRequest.Builder()
                .setDisplayName( fullName )
                .build()
        )

        view.onSuccess()
    }
}