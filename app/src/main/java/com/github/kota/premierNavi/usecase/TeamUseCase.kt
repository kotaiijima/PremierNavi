package com.github.kota.premierNavi.usecase

import com.github.kota.premierNavi.data.api.model.teamModel.ApiTeam
import com.github.kota.premierNavi.data.api.model.teamModel.Squad
import com.github.kota.premierNavi.domain.FootballDataRepository
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.Coach
import com.github.kota.premierNavi.domain.model.Player
import com.github.kota.premierNavi.domain.model.TeamDomainModel
import com.github.kota.premierNavi.domain.service.TeamApiService
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import javax.inject.Inject

class TeamUseCase @Inject constructor(
  private val teamApiService: TeamApiService
  ) {
  suspend operator fun invoke(teamId: TeamIdDomainObject):  ApiResult<TeamDomainModel>{
	  return teamApiService.getTeam(teamId)
  }
}