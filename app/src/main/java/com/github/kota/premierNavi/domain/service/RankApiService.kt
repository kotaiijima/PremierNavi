package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.data.api.model.rankingModel.ApiRank
import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

interface RankApiService {
	suspend fun convertRank(apiRank: ApiResult<ApiRank>): RequestState<RankDomainModel>
}