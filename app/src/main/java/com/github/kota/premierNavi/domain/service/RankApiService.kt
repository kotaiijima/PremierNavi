package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.utils.ApiResult

interface RankApiService {
	suspend fun getRank(): ApiResult<RankDomainModel>
}