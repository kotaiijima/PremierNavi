package com.github.kota.premierNavi.data.repository

import com.github.kota.premierNavi.data.api.LatestGameApi
//import com.github.kota.premierNavi.data.api.NextGameApi
import com.github.kota.premierNavi.data.api.model.latestGameModel.Team
import javax.inject.Inject

class FootballDataRepository @Inject constructor(
	private val latestGameApi: LatestGameApi,
//	private val nextGameApi: NextGameApi
){
	suspend fun getLatestGame(teamNumber: Int): Team{
		return latestGameApi.getLatestGame(teamNumber)
	}
//
//	suspend fun getNextGame(teamNumber: Int): Team{
//		return nextGameApi.getNextGame(teamNumber)
//	}
}