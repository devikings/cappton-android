package com.brunocardoso.capptonandroid.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.brunocardoso.capptonandroid.infra.database.AppDatabase
import com.brunocardoso.capptonandroid.schedule.repository.dao.AuthorDao
import com.brunocardoso.capptonandroid.schedule.repository.dao.ScheduleDao
import com.brunocardoso.capptonandroid.schedule.repository.data.Author
import com.brunocardoso.capptonandroid.schedule.repository.data.Schedule
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ScheduleDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var authorDao: AuthorDao
    private lateinit var scheduleDao: ScheduleDao
    private val scheduleA = Schedule("Schedule 1", "Details schedule 1", "Description schedule 1", "1", 1)
    private val scheduleB = Schedule("Schedule 2", "Details schedule 2", "Description schedule 2", "1", 2)
    private val scheduleC = Schedule("Schedule 3", "Details schedule 3", "Description schedule 3", "1", 3)
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
        scheduleDao = database.scheduleDao()

        authorDao.insertAll(listOf(authorA, authorB, authorC))
        scheduleDao.insertAll(listOf(scheduleA, scheduleB, scheduleC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetSchedulesOpened() {
        val uid = "1"
        val states = false
        val scheduleList = scheduleDao.getAllSchedules(uid, states)
        Assert.assertThat(scheduleList.size, Matchers.equalTo(3))

        // Teste ordem decrescente
        Assert.assertThat(scheduleList.get(0), Matchers.equalTo(scheduleC))
        Assert.assertThat(scheduleList.get(1), Matchers.equalTo(scheduleB))
        Assert.assertThat(scheduleList.get(2), Matchers.equalTo(scheduleA))
    }


    @Test
    fun testChangeFinishedSchedules() {
        var id = 1
        // Teste do evento para finalizar uma pauta
        val schedule = scheduleDao.getSchedule(id)
        Assert.assertFalse(schedule.status)

        // altera o status para true
        schedule.status = true
        scheduleDao.update(schedule)

        // recupera a mesma pauta para verificar se o status foi alterado
        val scheduleUpdated = scheduleDao.getSchedule(id)
        Assert.assertTrue(scheduleUpdated.status)

    }


    @Test
    fun testGetSchedule() {
        Assert.assertThat(scheduleDao.getSchedule(2), Matchers.equalTo(scheduleB))
    }
}