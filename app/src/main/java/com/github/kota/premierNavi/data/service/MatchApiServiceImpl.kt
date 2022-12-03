package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.MatchApi
import com.github.kota.premierNavi.data.api.model.matchModel.ApiMatch
import com.github.kota.premierNavi.data.ext.mapToDomainObject
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.*
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import retrofit2.HttpException
import javax.inject.Inject

class MatchApiServiceImpl @Inject constructor(
		private val matchApi: MatchApi
): MatchApiService {
	override suspend fun getMatch(
		teamId: TeamIdDomainObject,
		matchStatus: String,
	): ApiResult<MatchDomainModel> {
		return try {
			val response = matchApi.getMatch(teamId.value, matchStatus)
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
