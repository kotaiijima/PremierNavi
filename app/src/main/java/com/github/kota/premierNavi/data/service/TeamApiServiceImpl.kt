package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.TeamApi
import com.github.kota.premierNavi.data.ext.mapToDomainObject
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import retrofit2.HttpException
import javax.inject.Inject

class TeamApiServiceImpl @Inject constructor(
	private val teamApi: TeamApi
): TeamApiService {
	override suspend fun getTeam(teamId: TeamIdDomainObject): ApiResult<TeamDomainModel> {
		return try {
			val response = teamApi.getTeam(teamId.value)
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