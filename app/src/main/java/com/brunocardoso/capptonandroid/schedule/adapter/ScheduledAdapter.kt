package com.brunocardoso.capptonandroid.schedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import kotlinx.android.synthetic.main.item_schedule.view.*

class ScheduleAdapter(
    var list: MutableList<Schedule>,
    val listener: (Schedule) -> Unit): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    fun removeItem(i: Int){
        list.removeAt(i)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    open class ScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item: Schedule, listener: (Schedule) -> Unit) = with(itemView) {

            item_scheduled_title.text = item.title
            item_scheduled_details.text = item.details
            item_scheduled_desc.text = item.desc

            if (item.status){
                iv_icon_state.setImageResource(R.drawable.ic_assignment_turned_in_24dp)
                btn_finalizar.text = "Open"
            }else{
                iv_icon_state.setImageResource(R.drawable.ic_assignment_24dp)
                btn_finalizar.text = "Close"
            }

            itemView.setOnClickListener{
                val expanded = item.expanded
                if(expanded == false){
                    item_scheduled_desc.visibility = View.VISIBLE
                    btn_finalizar.visibility = View.VISIBLE
                }else{
                    item_scheduled_desc.visibility = View.GONE
                    btn_finalizar.visibility = View.GONE
                }

                item.expanded = !expanded
                item.status = !item.status
            }

            btn_finalizar.setOnClickListener {
                listener(item)
            }
        }

    }
}