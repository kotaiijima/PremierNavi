package com.github.kota.premierNavi.data.service

import com.github.kota.premierNavi.data.api.TeamApi
import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.handleApi
import javax.inject.Inject

class TeamApiServiceImpl @Inject constructor(
  private val teamApi: TeamApi
) : TeamApiService {
  override suspend fun getTeam(teamNumber: Int): ApiResult<Team> {
    return handleApi { teamApi.getTeam(teamNumber) }
  }
}
