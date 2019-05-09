package com.brunocardoso.capptonandroid.infra.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.brunocardoso.capptonandroid.R
import kotlin.reflect.KClass

open class BaseActivity: AppCompatActivity(){

    fun openActivity(
        activity: KClass<out AppCompatActivity>,
        bundle: Bundle? ){
        val intent = Intent(this, activity.java)
        startActivity(intent)
    }

    fun openActivityForResult(
        activity: KClass<out AppCompatActivity>,
        requestCode: Int, bundle: Bundle? ){
        val intent = Intent(this, activity.java)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }


    fun replaceFragmentOnContainer(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}