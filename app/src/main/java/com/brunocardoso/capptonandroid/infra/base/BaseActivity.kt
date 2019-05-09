package com.brunocardoso.capptonandroid.infra.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.brunocardoso.capptonandroid.R
import kotlin.reflect.KClass

open class BaseActivity: AppCompatActivity(){

    fun openActivity(
        activity: KClass<out AppCompatActivity>,
        bundle: Bundle? ){
        val intent = Intent(this, activity.java)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivity(intent)
    }

    fun openActivityForResult(
        activity: KClass<out AppCompatActivity>,
        requestCode: Int, bundle: Bundle? ){
        val intent = Intent(this, activity.java)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivityForResult(intent, requestCode)
    }


    fun replaceFragmentOnContainer(container: Int, fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
    }
}