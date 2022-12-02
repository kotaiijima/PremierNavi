package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState

  interface TeamApiService {
  suspend fun convertTeam(teamId: TeamIdDomainObject): ApiResult<ApiTeam>
}