package com.brunocardoso.capptonandroid.schedule.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.brunocardoso.capptonandroid.schedule.repository.dao.AuthorDao
import com.brunocardoso.capptonandroid.schedule.repository.data.Author

class ScheduleRepository(private val authorDao: AuthorDao) {
    val allAuthors: LiveData<List<Author>> = authorDao.getAllAuthors()

    @WorkerThread
    suspend fun insert(author: Author){
        authorDao.insert(author)
    }
}