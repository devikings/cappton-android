package com.brunocardoso.capptonandroid.schedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled
import kotlinx.android.synthetic.main.item_schedule.view.*

class ScheduleAdapter(var list: List<Scheduled>, val listener: (Scheduled) -> Unit): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) = holder.bind(list[position], listener)

    override fun getItemCount(): Int = list.size

    open class ScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item: Scheduled, listener: (Scheduled) -> Unit) = with(itemView) {
            item_scheduled_title.text = item.titulo
            setOnClickListener { listener(item) }
        }

    }
}