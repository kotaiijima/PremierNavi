package com.github.kota.premierNavi

import com.github.kota.premierNavi.domain.FootballDataRepository
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.usecase.TeamUseCase
import com.github.kota.premierNavi.utils.ApiResult
import com.github.kota.premierNavi.utils.RequestState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiTeamUseCaseTest {

  private lateinit var useCase: TeamUseCase
  private val fakeTeamApiService = FakeTeamApiService()

  @Before
  fun setUp() {
    useCase = TeamUseCase(fakeTeamApiService)
  }

  @Test
  fun getTeamTest() = runTest {
    val expected = ApiResult.Idle
	val actual = useCase.invoke(TeamIdDomainObject(57))
    Assert.assertEquals(expected, actual)
  }
}
