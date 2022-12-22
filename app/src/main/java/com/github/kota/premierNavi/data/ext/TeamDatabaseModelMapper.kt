package com.github.kota.premierNavi.data.ext

import com.github.kota.premierNavi.data.databaseModel.TeamInformationDatabaseModel
import com.github.kota.premierNavi.data.databaseModel.TeamPlayerDatabaseModel
import com.github.kota.premierNavi.domain.model.CoachDomainModel
import com.github.kota.premierNavi.domain.model.TeamDatabaseModel
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.model.TeamPlayerDomainModel
import java.time.LocalDate

fun TeamDatabaseModel.mapToDomainObject(): TeamDomainModel {
	return 	TeamDomainModel(
		teamId = this.teamInformation.teamId,
		updateTime = LocalDate.now().toString(),
		crest = this.teamInformation.crest,
		teamName = this.teamInformation.teamName,
		stadium = this.teamInformation.stadium,
		squad = listToPlayerDomainModel(this.teamPlayer),
		coach = CoachDomainModel(
			dateOfBirth = this.teamInformation.coach_dateOfBirth,
			coachName = this.teamInformation.coach_name,
			nationality = this.teamInformation.coach_nationality
		)
	)
}

private fun listToPlayerDomainModel(
	squad: List<TeamPlayerDatabaseModel>
) : List<TeamPlayerDomainModel> {
	val players = mutableListOf<TeamPlayerDomainModel>()
	squad.forEach { player ->
		players.add(
			TeamPlayerDomainModel(
				playerId = player.playerId,
				dateOfBirth = player.dateOfBirth,
				playerName = player.playerName,
				nationality = player.nationality,
				position = player.position
			)
		)
	}
	return players
}

fun TeamDomainModel.mapToDatabaseObject(): TeamDatabaseModel {
	return 	TeamDatabaseModel(
		teamInformation = TeamInformationDatabaseModel(
			teamId = this.teamId,
			updateTime = this.updateTime,
			crest = this.crest,
			stadium = this.stadium,
			teamName = this.teamName,
			coach_dateOfBirth = this.coach.dateOfBirth,
			coach_name = this.coach.coachName,
			coach_nationality = this.coach.nationality
		),
		teamPlayer = listToPlayerDatabaseModel(
			this.squad,
			this.teamId
		)
	)
}

private fun listToPlayerDatabaseModel(
	squad: List<TeamPlayerDomainModel>,
	belongTeamId: Int
) : List<TeamPlayerDatabaseModel> {
	val players = mutableListOf<TeamPlayerDatabaseModel>()
	squad.forEach { player ->
		players.add(
			TeamPlayerDatabaseModel(
				playerId = player.playerId,
				belongTeamId = belongTeamId,
				dateOfBirth = player.dateOfBirth,
				playerName = player.playerName,
				nationality = player.nationality,
				position = player.position
			)
		)
	}
	return players
}

