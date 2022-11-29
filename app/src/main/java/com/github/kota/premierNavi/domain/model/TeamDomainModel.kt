package com.github.kota.premierNavi.domain.model


data class TeamDomainModel(
	val crest: String,
	val stadium: String,
	val teamName: String,
	val squad: List<Player>,
	val coach: Coach
)

data class Player(
	val dateOfBirth: String,
	val playerName: String,
	val nationality: String,
	val position: String
)

data class Coach(
	val dateOfBirth: String,
	val coachName: String,
	val nationality: String,
)