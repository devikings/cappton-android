package com.brunocardoso.capptonandroid.schedule.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.schedule.presenter.SchedulePresenter
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import com.brunocardoso.capptonandroid.schedule.view.ScheduleView
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.act_schedule_create.*

class ScheduleCreateActivity : BaseActivity(), ScheduleView {

    private var adapter: ArrayAdapter<String>? = null
    private lateinit var listAuthors: List<Author>
    private lateinit var presenter: SchedulePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_schedule_create)

        initViews()
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
            val desc = edt_sched_desc.text.toString()

            if (title.equals("") || desc.equals("")){

                val snackBar = Snackbar.make(it, "Please, fill all inputs and try again! ", Snackbar.LENGTH_LONG)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.view.setBackgroundColor(Color.RED)
                snackBar.show()

            }else{

                // get author
                val authorSelected = listAuthors.get(sp_author.selectedItemPosition)

                // get user
                val userLogged = UserPresenter.getUser()

                presenter.create( Schedule(0, title, desc, false, userLogged?.uid!!, authorSelected.id!! ))
            }
        }
    }

    private fun configureSpinner(listAuthors: ArrayList<String>) {
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listAuthors)
        sp_author.adapter = adapter
    }

    override fun onSuccess() {
        val snackBar = Snackbar.make(btn_schedule_create, "Schedule create with success! ", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.view.setBackgroundColor(Color.GREEN)
        snackBar.show()

        finish()
    }

    override fun onError(error: String) {
        val snackBar = Snackbar.make(btn_schedule_create, "Error at create schedule, try again! ", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.view.setBackgroundColor(Color.RED)
        snackBar.show()
    }
}
