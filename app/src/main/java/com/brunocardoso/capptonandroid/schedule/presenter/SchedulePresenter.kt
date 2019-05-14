package com.brunocardoso.capptonandroid.schedule.presenter

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brunocardoso.capptonandroid.infra.database.AppDatabase
import com.brunocardoso.capptonandroid.schedule.repository.ScheduleRepository
import com.brunocardoso.capptonandroid.schedule.repository.dao.AuthorDao
import com.brunocardoso.capptonandroid.schedule.repository.dao.ScheduleDao
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import com.brunocardoso.capptonandroid.schedule.view.ScheduleView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SchedulePresenter(
    val context: Context,
    val callback: ScheduleView? = null
) {
    private lateinit var db: AppDatabase
    private var repository: ScheduleRepository? = null

    init{
        db = AppDatabase.getInstance(context)
        repository = ScheduleRepository.getInstance(db)
    }

    // Regras de negocio
    // criar
    // excluir
    // mudar status
    // recuperar pautas 1/

    //Schedules
    fun create(schedule: Schedule){
        repository?.insertSchedule(schedule, {
            callback?.onSuccess()
        })
    }
    fun delete(id: Int){ callback?.onSuccess() }

    fun update(schedule: Schedule){
        repository?.updateSchedule(schedule, {
            val list = repository?.schedules?.value
            list?.remove( schedule )
            repository?.schedules?.postValue(list)
            callback?.onSuccess()
        })
    }

    fun getSchedules(uid: String, status: Boolean): LiveData<MutableList<Schedule>>? = repository?.getSchedules(uid, status)

    // Authors
    fun getAuthors(id: Int) = repository?.getAuthor(id)

    fun getAuthors() = repository?.getAuthors()


}