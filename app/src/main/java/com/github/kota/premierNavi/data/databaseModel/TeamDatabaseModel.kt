package com.github.kota.premierNavi.data.databaseModel

import androidx.room.*
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

@Entity(
	tableName = DATABASE_TEAM_PLAYERS,
	foreignKeys = [ForeignKey(
		entity = TeamInformationDatabaseModel::class,
		parentColumns = arrayOf("teamId"),
		childColumns = arrayOf("belongTeamId"),
		onDelete = ForeignKey.CASCADE
	)]
)
data class TeamPlayerDatabaseModel(
	@PrimaryKey val playerId: Int,
	@ColumnInfo(index = true) val belongTeamId: Int,
	val dateOfBirth: String,
	val playerName: String,
	val nationality: String,
	val position: String
)

data class TeamDatabaseModel(
	@Embedded val teamInformation: TeamInformationDatabaseModel,
	@Relation(
		parentColumn = "teamId",
		entityColumn = "belongTeamId"
	)
	val teamPlayer: List<TeamPlayerDatabaseModel>
)