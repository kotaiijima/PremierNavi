package com.github.kota.premierNavi.data.databaseModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kota.premierNavi.utils.Constants.DATABASE_TEAM
import com.github.kota.premierNavi.utils.Constants.DATABASE_TEAM_PLAYERS

@Entity(tableName = DATABASE_TEAM)
data class TeamInformationDatabaseModel(
	@PrimaryKey val teamId: Int,
	val updateTime: String,
	val crest: String,
	val stadium: String,
	val teamName: String,
	val coach_dateOfBirth: String,
	val coach_name: String,
	val coach_nationality: String,
	)

@Entity(tableName = DATABASE_TEAM_PLAYERS)
data class TeamPlayerDatabaseModel(
	@PrimaryKey val playerId: Int,
	val belongTeamId: Int,
	val dateOfBirth: String,
	val playerName: String,
	val nationality: String,
	val position: String
)