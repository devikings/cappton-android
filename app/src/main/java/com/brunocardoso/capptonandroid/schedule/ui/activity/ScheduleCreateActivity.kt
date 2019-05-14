package com.brunocardoso.capptonandroid.schedule.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.afterTextChanged
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.infra.utils.snackbarBuilder
import com.brunocardoso.capptonandroid.schedule.presenter.SchedulePresenter
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import com.brunocardoso.capptonandroid.schedule.view.ScheduleView
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import kotlinx.android.synthetic.main.act_schedule_create.*

class ScheduleCreateActivity : BaseActivity(), ScheduleView {

    private var adapter: ArrayAdapter<String>? = null
    private lateinit var listAuthors: List<Author>
    private lateinit var presenter: SchedulePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_schedule_create)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        initViews()

        verifyInputsFill()
    }

    private fun verifyInputsFill() {
        edt_sched_title.afterTextChanged { changeStatesButton() }
        edt_sched_details.afterTextChanged { changeStatesButton() }
        edt_sched_desc.afterTextChanged { changeStatesButton() }
    }

    private fun changeStatesButton(){
        btn_schedule_create.isEnabled = edt_sched_title.text?.length!! > 0
                && edt_sched_details.text?.length!!> 0
                && edt_sched_desc.text?.length!! > 0
    }

    fun initViews(){
        presenter = SchedulePresenter(baseContext, this)
        presenter.getAuthors()?.observeForever(Observer {
            listAuthors = it
            val list = ArrayList<String>()
            for (author in it){
                list.add(author.name)
            }

            configureSpinner(list)
        })


        btn_schedule_create.setOnClickListener{
            val title = edt_sched_title.text.toString()
            val details = edt_sched_details.text.toString()
            val desc = edt_sched_desc.text.toString()

            if (title.equals("")
                || details.equals("")
                || desc.equals("")){

                snackbarBuilder(it, "Please, fill all inputs and try again!", Color.WHITE, Color.RED)

            }else{

                // get author
                val authorSelected = listAuthors.get(sp_author.selectedItemPosition)

                // get user
                val userLogged = UserPresenter.getUser()

                presenter.create( Schedule(title, details, desc, userLogged?.uid!!, authorSelected.id!! ))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configureSpinner(listAuthors: ArrayList<String>) {
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listAuthors)
        sp_author.adapter = adapter
    }

    override fun onSuccess() {
        snackbarBuilder(btn_schedule_create, "Schedule create with success!", Color.BLACK, Color.GREEN)
        finish()
    }

    override fun onError(error: String) {
        snackbarBuilder(btn_schedule_create, "Error at create schedule, try again!", Color.WHITE, Color.RED)
    }
}
