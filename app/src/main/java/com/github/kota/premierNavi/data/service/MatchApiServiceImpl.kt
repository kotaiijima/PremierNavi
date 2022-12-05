package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.MatchApi
import com.github.kota.premierNavi.data.ext.mapToDomainObject
import com.github.kota.premierNavi.domain.MatchStatus
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.*
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.utils.ApiResult
import retrofit2.HttpException
import javax.inject.Inject

class MatchApiServiceImpl @Inject constructor(
		private val matchApi: MatchApi
): MatchApiService {
	override suspend fun getMatch(
		teamId: TeamIdDomainObject,
		matchStatus: MatchStatus,
	): ApiResult<MatchDomainModel> {
		return try {
			val response = matchApi.getMatch(teamId.value, matchStatus.value)
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
