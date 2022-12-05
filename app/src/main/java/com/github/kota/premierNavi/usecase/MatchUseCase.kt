package com.github.kota.premierNavi.usecase

import com.github.kota.premierNavi.domain.MatchStatus
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.utils.ApiResult
import javax.inject.Inject

class MatchUseCase @Inject constructor(
  private val matchApiService: MatchApiService
  ) {
  suspend operator fun invoke(teamId: TeamIdDomainObject, matchStatus: MatchStatus):  ApiResult<MatchDomainModel>{
	  return matchApiService.getMatch(teamId, matchStatus)
  }
}