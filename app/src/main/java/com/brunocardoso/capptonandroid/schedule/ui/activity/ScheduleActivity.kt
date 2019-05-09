package com.brunocardoso.capptonandroid.schedule.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.schedule.ui.fragments.ScheduleListFragment
import com.brunocardoso.capptonandroid.schedule.ui.fragments.ScheduleRegisterFragment
import kotlinx.android.synthetic.main.act_schedule.*

class ScheduleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_schedule)

        replaceFragmentOnContainer(R.id.frame_container, ScheduleListFragment() )

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            android.R.id.home -> onBackPressed()
            R.id.action_perfil -> onBackPressed()
            R.id.action_logout -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun initViews(){
        btn_new_scheduled.setOnClickListener{
            replaceFragmentOnContainer(R.id.frame_container, ScheduleRegisterFragment())
        }
    }
}
