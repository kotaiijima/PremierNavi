package com.github.kota.premierNavi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kota.premierNavi.data.databaseModel.TeamId

@Database(
	entities = [TeamId::class],
	version = 2,
	exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
	abstract fun preNaviDao(): PreNaviDao
}