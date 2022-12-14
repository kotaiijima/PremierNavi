package com.github.kota.premierNavi.fake

import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

class FakeTeamApiService : TeamApiService {

  var getTeamImpl: () -> ApiResult<TeamDomainModel> = {
    ApiResult.Idle
  }

  override suspend fun getTeam(teamId: TeamIdDomainObject): ApiResult<TeamDomainModel> {
    return getTeamImpl()
  }
}