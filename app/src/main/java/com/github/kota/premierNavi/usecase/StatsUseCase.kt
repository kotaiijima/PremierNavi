package com.github.kota.premierNavi.usecase

import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.StatsDomainModel
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.utils.ApiResult
import javax.inject.Inject

class StatsUseCase @Inject constructor(
	private val statsApiService: StatsApiService
  ) {
  suspend operator fun invoke(teamId: TeamIdDomainObject):  ApiResult<StatsDomainModel>{
	  return statsApiService.getStats(teamId)
  }
}