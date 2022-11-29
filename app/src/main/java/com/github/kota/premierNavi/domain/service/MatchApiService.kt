package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.data.api.model.matchModel.ApiMatch
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

interface MatchApiService {
	suspend fun convertMatch(apiMatch: ApiResult<ApiMatch>): RequestState<MatchDomainModel>
}