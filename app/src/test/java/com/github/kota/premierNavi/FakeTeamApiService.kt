package com.github.kota.premierNavi

import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult


class FakeTeamApiService : TeamApiService {
  var getTeamImpl: () -> ApiResult<Team> = {
    ApiResult.Idle
  }
  override suspend fun getTeam(teamId: TeamIdDomainObject): ApiResult<Team> {
    return getTeamImpl()
  }
}