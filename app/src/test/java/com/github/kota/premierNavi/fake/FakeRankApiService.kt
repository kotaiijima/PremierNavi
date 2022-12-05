package com.github.kota.premierNavi.fake

import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.domain.service.RankApiService
import com.github.kota.premierNavi.utils.ApiResult

class FakeRankApiService : RankApiService {

  var getRankImpl: () -> ApiResult<RankDomainModel> = {
    ApiResult.Idle
  }

  override suspend fun getRank(): ApiResult<RankDomainModel> {
    return getRankImpl()
  }
}