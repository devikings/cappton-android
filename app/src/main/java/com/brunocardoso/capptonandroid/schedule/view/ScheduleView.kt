package com.brunocardoso.capptonandroid.schedule.view

import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule

interface ScheduleView {
    fun onSuccess()
    fun onError(error: String)
}