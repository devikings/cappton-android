package com.brunocardoso.capptonandroid.schedule.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Author(
    @PrimaryKey val id: Int? = null,
    val name: String
) {
    override fun toString() = name
}