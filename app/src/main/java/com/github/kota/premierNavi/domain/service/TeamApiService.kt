package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.data.api.model.teamModel.Team
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.utils.ApiResult

interface TeamApiService {
  suspend fun getTeam(teamId: TeamIdDomainObject): ApiResult<Team>
}

