package com.github.kota.premierNavi

import com.github.kota.premierNavi.usecase.TeamUseCase
import com.github.kota.premierNavi.utils.ApiResult
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class TeamUseCaseTest {

  private lateinit var teamUseCase: TeamUseCase
  private val fakeTeamApiService = FakeTeamApiService()

  @Before
  fun setUp() {
    teamUseCase = TeamUseCase(fakeTeamApiService)
  }

  @Test
  fun getTeamTest() {
    val expected = ApiResult.ApiError
    fakeTeamApiService.getTeamImpl = {
      ApiResult.ApiError
    }
    val actual = teamUseCase.invoke(1)
    Assert.assertEquals(expected, actual)
  }
}
