package com.brunocardoso.capptonandroid.schedule.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.infra.utils.snackbarBuilder
import com.brunocardoso.capptonandroid.perfil.ui.activity.PerfilEditActivity
import com.brunocardoso.capptonandroid.perfil.ui.fragments.PerfilFragment
import com.brunocardoso.capptonandroid.schedule.ui.fragments.ScheduleListFinishedFragment
import com.brunocardoso.capptonandroid.schedule.ui.fragments.ScheduleListOpenedFragment
import com.brunocardoso.capptonandroid.schedule.view.LogoutUserCallback
import com.brunocardoso.capptonandroid.schedule.view.ScheduleFragmentCallback
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.ui.activity.UserActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.act_perfil_edit.*
import kotlinx.android.synthetic.main.act_schedule.*
import kotlinx.android.synthetic.main.frag_user_reset_passw.*

class ScheduleActivity : BaseActivity(),
    ScheduleFragmentCallback,
    LogoutUserCallback,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_schedule)

        initViews()
    }

    override fun onStart() {
        super.onStart()

        replaceFragmentOnContainer(R.id.frame_container, ScheduleListOpenedFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuItem = menu?.getItem(0)
        menuItem?.isVisible = false

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_edit -> openActivityForResult(PerfilEditActivity::class, PERFIL_EDIT, null)
        }
        return super.onOptionsItemSelected(item)
    }

    fun initViews() {
        fab.setOnClickListener {
            openActivity(ScheduleCreateActivity::class)
        }

        // Bottom Navigation
        navigation_bottom.setOnNavigationItemSelectedListener(this)
    }

    private fun signout() {
        UserPresenter.logout()
        openActivity(UserActivity::class, null)
        finish()
    }

    /*
     * Callback from fragment register schedule
     */
    override fun onCreateListenner() {
    }

    /*
     * Callback from fragment update schedule
     */
    override fun onUpdateListenner() {
    }

    /*
     * Callback from fragment change status schedule
     */
    override fun onChangeStatusListenner() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_opened ->{
                replaceFragmentOnContainer(R.id.frame_container, ScheduleListOpenedFragment())
                fab.visibility = View.VISIBLE
                menuItem?.isVisible = false
            }
            R.id.action_finished -> {
                replaceFragmentOnContainer(R.id.frame_container, ScheduleListFinishedFragment())
                fab.visibility = View.VISIBLE
                menuItem?.isVisible = false
            }
            R.id.action_perfil -> {
                replaceFragmentOnContainer(R.id.frame_container, PerfilFragment())
                fab.visibility = View.GONE
                menuItem?.isVisible = true
            }
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            when(requestCode){
                PERFIL_EDIT -> {
                    navigation_bottom.menu.findItem(R.id.action_perfil).setChecked(true)

                    snackbarBuilder(fab, "Perfil edited with success!", Color.BLACK, Color.GREEN)
                }
                CREATE_SCHEDULE -> {

                    snackbarBuilder(fab, "Schedule create with success!", Color.BLACK, Color.GREEN)
                }
            }
        }
    }

    companion object {
        const val PERFIL_EDIT: Int = 100
        const val CREATE_SCHEDULE: Int = 200
    }

    override fun onLogout() {
        signout()
    }
}
