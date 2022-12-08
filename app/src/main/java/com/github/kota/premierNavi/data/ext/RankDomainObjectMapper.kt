package com.github.kota.premierNavi.data.ext

import com.github.kota.premierNavi.data.api.model.rankingModel.ApiRank
import com.github.kota.premierNavi.data.api.model.rankingModel.Table
import com.github.kota.premierNavi.domain.model.*

fun ApiRank.mapToDomainObject(): RankDomainModel {
	return RankDomainModel(
		listTeams(this.standings[0].table)
	)
}

private fun listTeams(table: List<Table>): List<Team> {
	val teams = mutableListOf<Team>()
	table.forEach{ team ->
		teams.add(
		Team(
			position = team.position.toString(),
			crest = team.team.crest,
			teamName = team.team.shortName,
			points = team.points.toString(),
			playedGame = team.playedGames.toString(),
			won = team.won.toString(),
			draw = team.draw.toString(),
			lost = team.lost.toString(),
			goalDifference = team.goalDifference.toString(),
			id = team.team.id
		)
		)
	}
	return teams
}