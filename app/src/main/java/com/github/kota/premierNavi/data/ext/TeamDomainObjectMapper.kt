package com.github.kota.premierNavi.data.ext

import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.data.api.model.teamModel.Squad
import com.github.kota.premierNavi.domain.model.*
import java.time.LocalDate


fun ApiTeam.mapToDomainObject(): TeamDomainModel {
	return 	TeamDomainModel(
		teamId = this.id,
		updateTime = LocalDate.now().toString(),
		crest = this.crest,
		teamName = this.name,
		stadium = this.venue,
		squad = listPlayers(this.squad),
		coach = CoachDomainModel(
			dateOfBirth = this.coach.dateOfBirth,
			coachName = this.coach.name,
			nationality = this.coach.nationality
		)
	)
}

private fun listPlayers(
	squad: List<Squad>
) : List<TeamPlayerDomainModel> {
	val players = mutableListOf<TeamPlayerDomainModel>()
		squad.forEach { player ->
			players.add(
				TeamPlayerDomainModel(
					playerId = player.id,
					dateOfBirth = player.dateOfBirth,
					playerName = player.name,
					nationality = player.nationality,
					position = player.position
				)
			)
	}
	return players
}