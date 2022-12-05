package com.github.kota.premierNavi.usecase

import com.github.kota.premierNavi.domain.model.RankDomainModel
import com.github.kota.premierNavi.domain.service.RankApiService
import com.github.kota.premierNavi.utils.ApiResult
import javax.inject.Inject

class RankUseCase @Inject constructor(
  private val rankApiService: RankApiService
  ) {
  suspend operator fun invoke():  ApiResult<RankDomainModel>{
	  return rankApiService.getRank()
  }
}