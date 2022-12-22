package com.github.kota.premierNavi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kota.premierNavi.data.dao.PreNaviDao
import com.github.kota.premierNavi.data.dao.TeamDao
import com.github.kota.premierNavi.data.databaseModel.TeamId
import com.github.kota.premierNavi.data.databaseModel.TeamInformationDatabaseModel
import com.github.kota.premierNavi.data.databaseModel.TeamPlayerDatabaseModel

@Database(
	entities = [TeamId::class, TeamInformationDatabaseModel::class, TeamPlayerDatabaseModel::class],
	version = 2,
	exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
	abstract fun preNaviDao(): PreNaviDao
	abstract fun teamDao(): TeamDao
}