package com.github.kota.premierNavi.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.github.kota.premierNavi.data.databaseModel.TeamInformationDatabaseModel
import com.github.kota.premierNavi.data.databaseModel.TeamPlayerDatabaseModel

data class TeamDomainModel(
	val teamId: Int,
	val updateTime: String,
	val crest: String,
	val stadium: String,
	val teamName: String,
	val squad: List<TeamPlayerDomainModel>,
	val coach: CoachDomainModel
)

data class TeamPlayerDomainModel(
	val playerId: Int,
	val dateOfBirth: String,
	val playerName: String,
	val nationality: String,
	val position: String
)

data class CoachDomainModel(
	val dateOfBirth: String,
	val coachName: String,
	val nationality: String,
)

data class TeamDatabaseModel(
	@Embedded val teamInformation: TeamInformationDatabaseModel,
	@Relation(
		parentColumn = "teamId",
		entityColumn = "belongTeamId"
	)
	val teamPlayer: List<TeamPlayerDatabaseModel>
)