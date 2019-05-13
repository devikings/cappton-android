package com.brunocardoso.capptonandroid.schedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import kotlinx.android.synthetic.main.item_schedule.view.*

class ScheduleAdapter(
    var list: List<Schedule>,
    val listener: (Schedule) -> Unit): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {

        holder.bind(list[position], listener)

    }

    override fun getItemCount(): Int = list.size

    open class ScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item: Schedule, listener: (Schedule) -> Unit) = with(itemView) {

            item_scheduled_title.text = item.title
            item_scheduled_desc.text = item.desc

            btn_finalizar.setOnClickListener { listener(item) }

            ll_read_more.setOnClickListener{
                val expanded = item.expanded

                if(expanded == false){
                    item_scheduled_desc.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    btn_finalizar.visibility = View.VISIBLE
                    iv_read_more.setImageResource(R.drawable.ic_keyboard_arrow_up_24dp)
                    item.expanded = true

                }else{
                    item_scheduled_desc.height = 50
                    btn_finalizar.visibility = View.GONE
                    iv_read_more.setImageResource(R.drawable.ic_keyboard_arrow_down_24dp)
                    item.expanded = false

                }
            }
        }

    }
}