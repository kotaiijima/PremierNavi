package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.StatsApi
import com.github.kota.premierNavi.data.ext.mapToDomainObject
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.utils.ApiResult
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
				ApiResult.Failure.ApiError(code = response.code(), message = response.message())
			}
		} catch (e: HttpException) {
			ApiResult.Failure.ApiError(code = e.code(), message = e.message())
		} catch (e: Throwable) {
			ApiResult.Failure.ApiException(e)
		}
	}
}