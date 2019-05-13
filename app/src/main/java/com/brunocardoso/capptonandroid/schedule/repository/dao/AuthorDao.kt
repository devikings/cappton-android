package com.brunocardoso.capptonandroid.schedule.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brunocardoso.capptonandroid.schedule.repository.data.Author

@Dao
interface AuthorDao {

    @Query("SELECT * from authors")
    fun getAllAuthors(): List<Author>

    @Query("SELECT * from authors WHERE id = :id")
    fun getAuthor(id: Int): Author

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(author: Author)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(author: List<Author>)

    @Query("DELETE FROM authors")
    fun removeAll()

}