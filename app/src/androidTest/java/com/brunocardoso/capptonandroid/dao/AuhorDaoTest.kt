package com.brunocardoso.capptonandroid.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry

import com.brunocardoso.capptonandroid.infra.database.AppDatabase
import com.brunocardoso.capptonandroid.schedule.repository.dao.AuthorDao
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AuhorDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var authorDao: AuthorDao
    private val authorA = Author(1, "Bruno")
    private val authorB = Author(2, "Pedro")
    private val authorC = Author(3, "Jessica")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        authorDao = database.authorDao()

        authorDao.insert(authorA)
        authorDao.insert(authorB)
        authorDao.insert(authorC)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAuthors() {
        val authorList = authorDao.getAllAuthors()
        Assert.assertThat(authorList.size, Matchers.equalTo(3))

        Assert.assertThat(authorList.get(0), Matchers.equalTo(authorA))
        Assert.assertThat(authorList.get(1), Matchers.equalTo(authorB))
        Assert.assertThat(authorList.get(2), Matchers.equalTo(authorC))
    }


    @Test
    fun testGetAuthor() {
        Assert.assertThat(authorDao.getAuthor(authorA.id!!), Matchers.equalTo(authorA))
    }
}