package com.brunocardoso.capptonandroid.schedule.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.schedule.adapter.ScheduleAdapter
import com.brunocardoso.capptonandroid.schedule.presenter.SchedulePresenter
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import com.brunocardoso.capptonandroid.schedule.view.ScheduleView
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.frag_schedules_opened.*

class ScheduleListOpenedFragment : Fragment(), ScheduleView {

    private lateinit var presenter: SchedulePresenter
    private lateinit var listSchedules: MutableList<Schedule>
    private var adapter: ScheduleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_schedules_opened, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val user = UserPresenter.getUser()

        presenter = SchedulePresenter(requireContext(), this)
        presenter.getSchedules(user?.uid!!,false)?.observe(viewLifecycleOwner, Observer {

            progress_bar.visibility = View.VISIBLE

            if (it.size>0) {

                listSchedules = it

                tv_no_data.visibility = View.GONE
                recycler_view_schedules.visibility = View.VISIBLE

                Thread(Runnable {

                    Thread.sleep(500)

                    activity?.runOnUiThread(java.lang.Runnable {
                        configureRecyclerView(it)
                        progress_bar.visibility = View.GONE
                    })

                }).start()
            }else{
                tv_no_data.visibility = View.VISIBLE
                recycler_view_schedules.visibility = View.GONE
                progress_bar.visibility = View.GONE
            }
        })
    }

    private fun configureRecyclerView(list: MutableList<Schedule>) {

        val layoutManager = LinearLayoutManager(requireContext())
        recycler_view_schedules.layoutManager = layoutManager

        adapter = ScheduleAdapter(list) {
            presenter.update(it)
        }

        recycler_view_schedules.adapter = adapter
    }


    override fun onSuccess() {
        val snackBar = Snackbar.make(recycler_view_schedules, "Scheduled finalized with success!", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.view.setBackgroundColor(Color.GREEN)
        snackBar.show()
    }

    override fun onError(error: String) {
        val snackBar = Snackbar.make(recycler_view_schedules, "Error at finishing schedule!", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.view.setBackgroundColor(Color.RED)
        snackBar.show()
    }
}
