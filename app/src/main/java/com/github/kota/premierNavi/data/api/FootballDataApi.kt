package com.github.kota.premierNavi.data.api

import com.github.kota.premierNavi.data.api.model.matchModel.Match
import com.github.kota.premierNavi.data.api.model.rankingModel.Rank
import com.github.kota.premierNavi.data.api.model.statsModel.Stats
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchApi {
		@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
		@GET("/v4/teams/{teamNumber}/matches?limit=1")
		suspend fun getMatch(
			@Path("teamNumber") teamNumber: Int,
			@Query("status") status: String
		): Response<Match>
}

interface TeamApi {
	@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
	@GET("/v4/teams/{teamNumber}/")
	suspend fun getTeam(
		@Path("teamNumber") teamNumber: Int
	): Response<Team>
}

interface RankApi {
	@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
	@GET("/v4/competitions/PL/standings/")
	suspend fun getRank(
	): Response<Rank>
}

interface StatsApi {
	@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
	@GET("/v4/teams/{teamNumber}/matches")
	suspend fun getStats(
		@Path("teamNumber") teamNumber: Int
	): Response<Stats>
}
