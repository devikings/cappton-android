package com.brunocardoso.capptonandroid.infra.database

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brunocardoso.capptonandroid.infra.AUTHOR_DATA_FILENAME
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(AUTHOR_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Author>>() {}.type
                    val plantList: List<Author> = Gson().fromJson(jsonReader, plantType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.authorDao().insertAll(plantList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}

