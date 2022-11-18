package com.github.kota.premierNavi.usecase

import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import javax.inject.Inject

class TeamUseCase @Inject constructor(
  private val teamApiService: TeamApiService
) {
  operator suspend fun invoke(teamNumber: Int): ApiResult<Team> {
    return teamApiService.getTeam(teamNumber)
  }
}
