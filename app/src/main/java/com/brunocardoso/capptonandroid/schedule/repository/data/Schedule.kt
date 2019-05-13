package com.brunocardoso.capptonandroid.schedule.repository.data

import androidx.room.*

@Entity(
    tableName = "schedules",
    primaryKeys = ["id"],
    foreignKeys = [ForeignKey(entity = Author::class,
        parentColumns = ["id"],
        childColumns = ["authorId"])]
)
data class Schedule(
    val id: Int? = null,
    val title: String,
    val desc: String,
    var expanded: Boolean,
    val uid: String,
    @ColumnInfo(name = "authorId", index = true) //just add index = true
    val authorId: Int
    )