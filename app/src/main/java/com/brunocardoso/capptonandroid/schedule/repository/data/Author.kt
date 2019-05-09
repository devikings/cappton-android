package com.brunocardoso.capptonandroid.schedule.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Author(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String
)