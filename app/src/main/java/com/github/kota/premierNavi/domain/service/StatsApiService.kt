package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.utils.ApiResult

interface StatsApiService {
	suspend fun getStats(teamId: TeamIdDomainObject): ApiResult<StatsDomainModel>
}