package com.github.kota.premierNavi.data.repository

import com.github.kota.premierNavi.data.api.MatchApi
import com.github.kota.premierNavi.data.api.model.matchModel.Team
import javax.inject.Inject

class FootballDataRepository @Inject constructor(
	private val matchApi: MatchApi,
){
	suspend fun getLatestGame(teamNumber: Int, matchStatus: String): Team{
		return matchApi.getMatch(teamNumber, matchStatus)
	}
}