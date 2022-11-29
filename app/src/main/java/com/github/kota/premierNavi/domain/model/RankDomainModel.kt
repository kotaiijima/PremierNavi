package com.github.kota.premierNavi.domain.model

data class RankDomainModel(
	val teams: List<Team>
)

data class Team(
	val position: String,
	val crest: String,
	val teamName: String,
	val points: String,
	val playedGame: String,
	val won: String,
	val draw: String,
	val lost: String,
	val goalDifference: String,
	val id: Int
)