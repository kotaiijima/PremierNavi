package com.github.kota.premierNavi.data.repository

import com.github.kota.premierNavi.data.api.MatchApi
import com.github.kota.premierNavi.data.api.RankApi
import com.github.kota.premierNavi.data.api.StatsApi
import com.github.kota.premierNavi.data.api.TeamApi
import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import javax.inject.Inject

class FootballDataRepository @Inject constructor(
	private val matchApi: MatchApi,
	private val teamApi: TeamApi,
	private val rankApi: RankApi,
	private val statsApi: StatsApi
){
	suspend fun getMatch(teamNumber: Int, matchStatus: String): Match{
		return matchApi.getMatch(teamNumber, matchStatus)
	}

	suspend fun getTeam(teamNumber: Int): Team{
		return teamApi.getTeam(teamNumber)
	}

	suspend fun getRank(): Rank {
		return rankApi.getRank()
	}

	suspend fun getStats(teamNumber: Int): Stats{
		return statsApi.getStats(teamNumber)
	}
}