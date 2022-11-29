package com.github.kota.premierNavi.data.api

import com.github.kota.premierNavi.data.api.model.matchModel.ApiMatch
import com.github.kota.premierNavi.data.api.model.rankingModel.ApiRank
import com.github.kota.premierNavi.data.api.model.statsModel.ApiStats
import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchApi {
		@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
		@GET("/v4/teams/{teamId}/matches?limit=1")
		suspend fun getMatch(
			@Path("teamId") teamId: Int,
			@Query("status") status: String
		): Response<ApiMatch>
}

interface TeamApi {
	@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
	@GET("/v4/teams/{teamId}/")
	suspend fun getTeam(
		@Path("teamId") teamId: Int
	): Response<ApiTeam>
}

interface RankApi {
	@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
	@GET("/v4/competitions/PL/standings/")
	suspend fun getRank(
	): Response<ApiRank>
}

interface StatsApi {
	@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
	@GET("/v4/teams/{teamId}/matches/")
	suspend fun getStats(
		@Path("teamId") teamId: Int
	): Response<ApiStats>
}
