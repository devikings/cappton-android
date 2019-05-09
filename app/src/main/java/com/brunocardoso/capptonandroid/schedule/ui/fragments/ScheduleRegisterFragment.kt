package com.brunocardoso.capptonandroid.schedule.ui.fragments

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
import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled
import com.brunocardoso.capptonandroid.schedule.view.RegisterViewListenner
import kotlinx.android.synthetic.main.frag_schedule_register.*

class ScheduleRegisterFragment : Fragment(), RegisterViewListenner {

    private lateinit var presenter: SchedulePresenter
    private var adapter: ScheduleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_schedule_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    /*
     * Scheduled create with successful
     */
    override fun onSuccess() {}

    /*
     * Erro at remove scheduled
     */
    override fun onError(error: String) {}
}
