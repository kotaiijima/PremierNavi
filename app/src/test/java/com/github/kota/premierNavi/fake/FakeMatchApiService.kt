package com.github.kota.premierNavi.fake

import com.github.kota.premierNavi.domain.MatchStatus
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.utils.ApiResult

class FakeMatchApiService : MatchApiService {

  var getMatchImpl: () -> ApiResult<MatchDomainModel> = {
    ApiResult.Idle
  }

  override suspend fun getMatch(teamId: TeamIdDomainObject, matchStatus: MatchStatus): ApiResult<MatchDomainModel> {
    return getMatchImpl()
  }
}