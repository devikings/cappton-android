package com.brunocardoso.capptonandroid.schedule.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brunocardoso.capptonandroid.schedule.repository.data.Author

@Dao
interface AuthorDao {

    @Query("SELECT * from authors ORDER BY name ASC")
    fun getAllAuthors(): LiveData<List<Author>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(author: Author)

    @Query("DELETE FROM authors")
    fun deleteAll()

}