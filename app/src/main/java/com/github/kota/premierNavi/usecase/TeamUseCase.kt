package com.github.kota.premierNavi.usecase

import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import javax.inject.Inject

class TeamUseCase @Inject constructor(
  private val teamApiService: TeamApiService
) {
  suspend operator fun invoke(teamId: Int): ApiResult<Team> {
    return teamApiService.getTeam(TeamIdDomainObject(teamId))
  }
}
