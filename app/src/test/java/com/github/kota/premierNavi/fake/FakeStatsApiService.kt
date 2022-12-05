package com.github.kota.premierNavi.fake

import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.utils.ApiResult

class FakeStatsApiService : StatsApiService {

  var getStatsImpl: () -> ApiResult<StatsDomainModel> = {
    ApiResult.Idle
  }

  override suspend fun getStats(teamId: TeamIdDomainObject): ApiResult<StatsDomainModel> {
    return getStatsImpl()
  }
}