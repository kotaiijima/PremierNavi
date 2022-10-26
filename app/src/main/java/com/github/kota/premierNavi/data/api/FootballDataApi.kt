package com.github.kota.premierNavi.data.api

import com.github.kota.premierNavi.data.api.model.matchModel.Team
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
		): Team
}