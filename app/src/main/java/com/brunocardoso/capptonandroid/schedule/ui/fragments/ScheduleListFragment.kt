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
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled
import com.brunocardoso.capptonandroid.schedule.view.ListViewListenner
import kotlinx.android.synthetic.main.frag_schedule_list.*

class ScheduleListFragment : Fragment(), ListViewListenner {

    private lateinit var presenter: SchedulePresenter
    private var adapter: ScheduleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_schedule_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SchedulePresenter(null, this)
        presenter.getListScheduled().observe(viewLifecycleOwner, Observer {
            configureRecyclerView(it)
        })

    }

    override fun onListListenner(list: List<Scheduled>) {
        configureRecyclerView(list)
    }

    private fun configureRecyclerView(list: List<Scheduled>) {

        val layoutManager = LinearLayoutManager(requireContext())
        recycler_view_schedules.layoutManager = layoutManager

        adapter = ScheduleAdapter(list) {
            presenter.add(Scheduled(7, "Teste 7", "Descricao do item 7", Author(1, "Bruno")))
        }
        recycler_view_schedules.adapter = adapter

    }


}
