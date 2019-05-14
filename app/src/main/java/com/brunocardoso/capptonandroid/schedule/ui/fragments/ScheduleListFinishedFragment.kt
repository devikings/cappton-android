package com.brunocardoso.capptonandroid.schedule.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.utils.snackbarBuilder
import com.brunocardoso.capptonandroid.schedule.adapter.ScheduleAdapter
import com.brunocardoso.capptonandroid.schedule.presenter.SchedulePresenter
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import com.brunocardoso.capptonandroid.schedule.view.ScheduleView
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.act_schedule_create.*
import kotlinx.android.synthetic.main.frag_schedules_finished.*

class ScheduleListFinishedFragment : Fragment(), ScheduleView{

    private lateinit var presenter: SchedulePresenter
    private var adapter: ScheduleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_schedules_finished, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = UserPresenter.getUser()

        presenter = SchedulePresenter(requireContext(), this)
        presenter.getSchedules(user?.uid!!, true)?.observe(viewLifecycleOwner, Observer {

            progress_bar.visibility = View.VISIBLE

            if (it.size>0) {

                tv_no_data.visibility = View.GONE

                Thread(Runnable {

                    Thread.sleep(500)

                    activity?.runOnUiThread(java.lang.Runnable {
                        configureRecyclerView(it)
                        progress_bar.visibility = View.GONE
                    })

                }).start()
            }else{
                tv_no_data.visibility = View.VISIBLE
                recycler_schedules_finished.visibility = View.GONE
                progress_bar.visibility = View.GONE
            }
        })
    }

    private fun configureRecyclerView(list: MutableList<Schedule>) {

        val layoutManager = LinearLayoutManager(requireContext())
        recycler_schedules_finished.layoutManager = layoutManager

        adapter = ScheduleAdapter(list) {
            presenter.update(it)
        }
        recycler_schedules_finished.adapter = adapter

    }

    override fun onSuccess() {
        snackbarBuilder(recycler_schedules_finished, "Scheduled opened with success!", Color.BLACK, Color.GREEN)
    }

    override fun onError(error: String) {
        snackbarBuilder(recycler_schedules_finished, "Error at opened schedule!", Color.WHITE, Color.RED)
    }
}
