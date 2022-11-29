package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.data.api.model.statsModel.ApiStats
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

interface StatsApiService {
	suspend fun convertStats(apiStats: ApiResult<ApiStats>): RequestState<StatsDomainModel>
}