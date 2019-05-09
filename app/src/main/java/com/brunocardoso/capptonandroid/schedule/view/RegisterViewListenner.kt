package com.brunocardoso.capptonandroid.schedule.view

import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled

interface RegisterViewListenner {
    fun onSuccess()
    fun onError(error: String)
}