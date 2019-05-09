package com.brunocardoso.capptonandroid.infra.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brunocardoso.capptonandroid.schedule.repository.dao.AuthorDao
import com.brunocardoso.capptonandroid.schedule.repository.data.Author

@Database(entities = [Author::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun authorDao(): AuthorDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cappton_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}