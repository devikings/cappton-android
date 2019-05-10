package com.brunocardoso.capptonandroid.schedule.repository.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
data class Scheduled(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val titulo: String,
    val desc: String,
    val author: Author)