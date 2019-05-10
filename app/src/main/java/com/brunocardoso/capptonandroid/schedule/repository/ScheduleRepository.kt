package com.brunocardoso.capptonandroid.schedule.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brunocardoso.capptonandroid.schedule.repository.dao.AuthorDao
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Scheduled
import java.util.*

object ScheduleRepository {

    private val schedules: List<Scheduled> = listOf(
        Scheduled(1, "Teste 1", "Descricao do Teste 1", Author(1, "Bruno")),
        Scheduled(2, "Teste 2", "Descricao do Teste 2", Author(2, "Pedro")),
        Scheduled(3, "Teste 3", "Descricao do Teste 3", Author(1, "Bruno")),
        Scheduled(4, "Teste 4", "Descricao do Teste 4", Author(2, "Pedro")),
        Scheduled(5, "Teste 5", "Descricao do Teste 5", Author(2, "Bruno")),
        Scheduled(6, "Teste 6", "Descricao do Teste 6", Author(1, "Bruno")))

    private val _listSchedule = MutableLiveData<List<Scheduled>>()

    val listSchedule: LiveData<List<Scheduled>>
        get() = _listSchedule

    init {
        _listSchedule.value = schedules
    }

    fun getSchedule(id: Int): Scheduled = schedules[Random().nextInt(schedules.size)]

}