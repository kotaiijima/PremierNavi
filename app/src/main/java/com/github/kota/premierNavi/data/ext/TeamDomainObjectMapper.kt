package com.github.kota.premierNavi.data.ext

import com.github.kota.premierNavi.data.api.model.matchModel.ApiMatch
import com.github.kota.premierNavi.data.api.model.rankingModel.ApiRank
import com.github.kota.premierNavi.data.api.model.rankingModel.Table
import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.data.api.model.teamModel.Squad
import com.github.kota.premierNavi.domain.model.*



fun ApiTeam.mapToDomainObject(): TeamDomainModel {
	return 	TeamDomainModel(
		crest = this.crest,
		teamName = this.name,
		stadium = this.venue,
		squad = listPlayers(this.squad),
		coach = Coach(
			dateOfBirth = this.coach.dateOfBirth,
			coachName = this.coach.name,
			nationality = this.coach.nationality
		)
	)
}

private fun listPlayers(
	squad: List<Squad>
) : List<Player> {
	val players = mutableListOf<Player>()
		squad.forEach { player ->
			players.add(
				Player(
					dateOfBirth = player.dateOfBirth,
					playerName = player.name,
					nationality = player.nationality,
					position = player.position
				)
			)
	}
	return players
}