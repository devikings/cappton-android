package com.brunocardoso.capptonandroid.schedule.repository.data

import androidx.room.*

@Entity(
    tableName = "schedules",
    foreignKeys = [ForeignKey(entity = Author::class,
        parentColumns = ["id"],
        childColumns = ["authorId"])]
)
data class Schedule(
    val title: String,
    val details: String,
    val desc: String,
    val uid: String,
    @ColumnInfo(name = "authorId", index = true) //just add index = true
    val authorId: Int,
    var status: Boolean = false
    )
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    @Ignore var expanded: Boolean = false
}