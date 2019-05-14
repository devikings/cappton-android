package com.brunocardoso.capptonandroid.schedule.repository.dao

import androidx.room.*
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule

@Dao
interface ScheduleDao {

    @Query("SELECT * from schedules WHERE uid = :uid AND status = :status ORDER BY id DESC")
    fun getAllSchedules(uid: String, status: Boolean): MutableList<Schedule>

    @Query("SELECT * from schedules WHERE id = :id")
    fun getSchedule(id: Int): Schedule

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(schedule: Schedule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(schedule: List<Schedule>)

    @Update
    fun update(schedule: Schedule)
}