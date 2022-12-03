package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.StatsApi
import com.github.kota.premierNavi.data.api.model.statsModel.ApiStats
import com.github.kota.premierNavi.data.api.model.statsModel.Match
import com.github.kota.premierNavi.data.ext.mapToDomainObject
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.MatchInformation
import com.github.kota.premierNavi.domain.model.Score
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.model.TeamInformation
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import retrofit2.HttpException
import javax.inject.Inject

class StatsApiServiceImpl @Inject constructor(
	private val statsApi: StatsApi
): StatsApiService {
	override suspend fun getStats(teamId: TeamIdDomainObject): ApiResult<StatsDomainModel> {
		return try {
			val response = statsApi.getStats(teamId.value)
			val body = response.body()
			if (response.isSuccessful && body != null) {
				ApiResult.ApiSuccess(body.mapToDomainObject())
			} else {
				ApiResult.ApiError(code = response.code(), message = response.message())
			}
		} catch (e: HttpException) {
			ApiResult.ApiError(code = e.code(), message = e.message())
		} catch (e: Throwable) {
			ApiResult.ApiException(e)
		}
	}
}