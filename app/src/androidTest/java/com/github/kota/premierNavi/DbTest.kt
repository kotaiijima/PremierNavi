package com.github.kota.premierNavi

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.kota.premierNavi.data.AppDatabase
import com.github.kota.premierNavi.data.PreNaviDao
import com.github.kota.premierNavi.data.databaseModel.TeamId
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class DbTest {
	private lateinit var preNaviDao: PreNaviDao
	private lateinit var db: AppDatabase

	private val testDispatcher = StandardTestDispatcher()

	@Before
	fun createDb() {
		Dispatchers.setMain(testDispatcher)

		val context = ApplicationProvider.getApplicationContext<Context>()
		db = Room
			.inMemoryDatabaseBuilder(
			context, AppDatabase::class.java)
			.setTransactionExecutor(Executors.newSingleThreadExecutor())
			.build()
		preNaviDao = db.preNaviDao()
	}

	@After
	@Throws(IOException::class)
	fun closeDb() {
		db.close()

		Dispatchers.resetMain()
		testDispatcher.cancel()
	}

	@Test
	@Throws(Exception::class)
	fun writeTeamIdAndRead() = runBlocking {
		val teamId = TeamId(
			id = 1,
			teamId = 57
		)
		preNaviDao.addTeamId(teamId)
		val job = launch(Dispatchers.IO) {
			preNaviDao.getTeamId().collect { byTeamId ->
				Assert.assertEquals(byTeamId, teamId)
			}
		}
		job.cancel()
	}

	@Test
	@Throws(Exception::class)
	fun updateTeamId() = runBlocking {
		val teamId = TeamId(
			id = 1,
			teamId = 46
		)
		preNaviDao.updateTeamId(teamId)
		val job = launch(Dispatchers.IO) {
			preNaviDao.getTeamId().collect { byTeamId ->
				Assert.assertEquals(byTeamId, teamId)
			}
		}
		job.cancel()
	}
}