package com.github.kota.premierNavi.data.api

import com.github.kota.premierNavi.data.api.model.latestGameModel.Team
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface LatestGameApi {
		@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
		@GET("/v4/teams/{teamNumber}/matches?status=FINISHED&limit=1")
		suspend fun getLatestGame(
			@Path("teamNumber") teamNumber: Int
		): Team
}

//interface NextGameApi {
//	@Headers("X-Auth-Token: d7c5b36a10114765b9615d549dab8b5c")
//	@GET("/v4/teams/{teamNumber}/matches?status=SCHEDULED&limit=1")
//	suspend fun getNextGame(
//		@Path("teamNumber") teamNumber: Int
//	): Team
//}