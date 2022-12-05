package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.RankApi
import com.github.kota.premierNavi.data.ext.mapToDomainObject
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.domain.service.RankApiService
import com.github.kota.premierNavi.utils.ApiResult
import retrofit2.HttpException
import javax.inject.Inject

class RankApiServiceImpl @Inject constructor(
	private val rankApi: RankApi
): RankApiService {
	override suspend fun getRank(): ApiResult<RankDomainModel> {
		return try {
			val response = rankApi.getRank()
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