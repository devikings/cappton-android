package com.brunocardoso.capptonandroid.schedule.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brunocardoso.capptonandroid.schedule.repository.ScheduleRepository
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled
import com.brunocardoso.capptonandroid.schedule.ui.fragments.ScheduleListFragment
import com.brunocardoso.capptonandroid.schedule.view.ListViewListenner


class SchedulePresenter(
    callbackRegister: ScheduleListFragment?,
    callbackList: ListViewListenner?
) {

    val listScheduled: LiveData<List<Scheduled>>
        get() = ScheduleRepository.listSchedule

    fun getScheduled(id: Int) = ScheduleRepository.getSchedule(id)

    // Regras de negocio
    // criar
    // excluir
    // mudar status
    // recuperar pautas 1/



}