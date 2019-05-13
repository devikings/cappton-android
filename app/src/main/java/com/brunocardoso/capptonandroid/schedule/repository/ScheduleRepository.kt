package com.brunocardoso.capptonandroid.schedule.repository

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brunocardoso.capptonandroid.infra.database.AppDatabase
import com.brunocardoso.capptonandroid.schedule.repository.dao.AuthorDao
import com.brunocardoso.capptonandroid.schedule.repository.dao.ScheduleDao
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ScheduleRepository private constructor(private val db: AppDatabase) {

    companion object {
        private var instance: ScheduleRepository? = null

        fun getInstance(db: AppDatabase) : ScheduleRepository? {
            if (instance == null){
                synchronized(this){
                    instance = ScheduleRepository(db)
                }
            }

            return instance
        }
    }

    private lateinit var scheduleDao: ScheduleDao
    private lateinit var authorDao: AuthorDao

    private lateinit var authors: MutableLiveData<List<Author>>
    private lateinit var author: MutableLiveData<Author>
    private lateinit var schedules : MutableLiveData<List<Schedule>>


    init {
        scheduleDao = db.scheduleDao()
        authorDao = db.authorDao()

        val listAuthors = authorDao.getAllAuthors()

        if (listAuthors.isEmpty() || listAuthors.size == 0) {
            // adiciona autores manualmente
            authorDao.insertAll(
                listOf(
                    Author(1, "Bruno Cardoso"),
                    Author(2, "Pedro Cardoso"),
                    Author(3, "Jessica Pastori")
                )
            )
        }

    }


    @UiThread
    fun getSchedules(): LiveData<List<Schedule>>{
        schedules = MutableLiveData()
        schedules.postValue(scheduleDao.getAllSchedules())

        GlobalScope.launch(Dispatchers.Main) {
            val schedulesFromDb: List<Schedule> = async(Dispatchers.IO) {
                return@async scheduleDao.getAllSchedules()
            }.await()

            schedules.value = schedulesFromDb
        }

        return schedules
    }

    @UiThread
    fun getAuthor(id: Int): LiveData<Author>{
        author = MutableLiveData()
        author.postValue(authorDao.getAuthor(id))

        GlobalScope.launch(Dispatchers.Main) {
            val authorFromDb: Author = async(Dispatchers.IO) {
                return@async authorDao.getAuthor(id)
            }.await()

            author.value = authorFromDb
        }

        return author
    }

    @UiThread
    fun getAuthors(): LiveData<List<Author>>{
        authors = MutableLiveData()
        authors.postValue(authorDao.getAllAuthors())

        GlobalScope.launch(Dispatchers.Main) {
            val authorsFromDb: List<Author> = async(Dispatchers.IO) {
                return@async authorDao.getAllAuthors()
            }.await()

            authors.value = authorsFromDb
        }

        return authors
    }

}