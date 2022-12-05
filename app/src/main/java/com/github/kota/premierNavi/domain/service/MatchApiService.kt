package com.github.kota.premierNavi.domain.service

import com.github.kota.premierNavi.domain.MatchStatus
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.domain.model.MatchDomainModel
import com.github.kota.premierNavi.utils.ApiResult

interface MatchApiService {
	suspend fun getMatch(teamId: TeamIdDomainObject, matchStatus: MatchStatus): ApiResult<MatchDomainModel>
}