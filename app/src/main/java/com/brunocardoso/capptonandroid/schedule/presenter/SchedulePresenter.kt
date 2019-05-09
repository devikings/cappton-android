package com.brunocardoso.capptonandroid.schedule.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled
import com.brunocardoso.capptonandroid.schedule.ui.fragments.ScheduleListFragment
import com.brunocardoso.capptonandroid.schedule.view.ListViewListenner


class SchedulePresenter(
    callbackRegister: ScheduleListFragment?,
    callbackList: ListViewListenner?
) {

    private var listScheduled: MutableLiveData<List<Scheduled>> = MutableLiveData()

    init {
        loadListShedule()
    }

    fun loadListShedule() {
        listScheduled.postValue(get())
    }

    fun getListScheduled(): LiveData<List<Scheduled>>{

        return listScheduled
    }

    // Regras de negocio
    // criar
    // excluir
    // mudar status
    // recuperar pautas 1/

    fun get(): List<Scheduled>? {
        return listOf(
            Scheduled(1, "Teste 1", "Descricao do Teste 1", Author(1, "Bruno")),
            Scheduled(2, "Teste 2", "Descricao do Teste 2", Author(2, "Pedro")),
            Scheduled(3, "Teste 3", "Descricao do Teste 3", Author(1, "Bruno")),
            Scheduled(4, "Teste 4", "Descricao do Teste 4", Author(2, "Pedro")),
            Scheduled(5, "Teste 5", "Descricao do Teste 5", Author(2, "Bruno")),
            Scheduled(6, "Teste 6", "Descricao do Teste 6", Author(1, "Bruno"))
        )
    }

    fun add(schedule: Scheduled){
        var list:MutableList<Scheduled> = ArrayList<Scheduled>()
        list.add(schedule)

        listScheduled.postValue(list)
    }



}