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

    fun signin(email: String, passw: String){
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, passw)
            .addOnCompleteListener {
                if (it.isSuccessful){

                    view.onSuccess()

                }else{
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signup(fullName: String, email: String, passw: String){
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, passw)
            .addOnCompleteListener({
                if (it.isSuccessful){

                    updateUser(fullName)
                    view.onSuccess()

                }else{
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun logout() {
        FirebaseAuth
            .getInstance()
            .signOut()
        view.onLogout()
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
    }
}