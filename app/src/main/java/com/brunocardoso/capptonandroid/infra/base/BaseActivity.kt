package com.brunocardoso.capptonandroid.infra.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import kotlin.reflect.KClass

open class BaseActivity: AppCompatActivity(){

    fun openActivity(
        activity: KClass<out AppCompatActivity>,
        bundle: Bundle? = null ){
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


    fun replaceFragmentOnContainer(container: Int, fragment: Fragment, tag: String = ""){
        supportFragmentManager.beginTransaction().apply {
            replace(container, fragment, tag)
            supportFragmentManager.findFragmentById(container)?.let { addToBackStack(null) }
            commit()
        }
    }

    fun popStackAndReplaceFragment(fragment: Fragment, container: Int, tag: String = "") {
        if (supportFragmentManager.findFragmentById(container)?.javaClass == fragment.javaClass) return
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .replace(container, fragment, tag)
            .commit()
    }
}