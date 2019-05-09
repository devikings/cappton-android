package com.brunocardoso.capptonandroid.schedule.view

import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled

interface ListViewListenner{
    fun onListListenner(list: List<Scheduled>)
}