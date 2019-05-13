package com.brunocardoso.capptonandroid.schedule.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule

@Dao
interface ScheduleDao {

    @Query("SELECT * from schedules")
    fun getAllSchedules(): List<Schedule>

    @Query("SELECT * from schedules WHERE id = :id")
    fun getSchedule(id: Int): Schedule

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(schedule: Schedule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(schedule: List<Schedule>)
}