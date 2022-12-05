package com.github.kota.premierNavi.useCaseTest

import com.github.kota.premierNavi.domain.MatchStatus
import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.fake.FakeMatchApiService
import com.github.kota.premierNavi.usecase.MatchUseCase
import com.github.kota.premierNavi.utils.ApiResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiMatchUseCaseTest {

	private lateinit var useCase: MatchUseCase
	private val fakeMatchApiService = FakeMatchApiService()

	@Before
	fun setUp() {
		useCase = MatchUseCase(fakeMatchApiService)
	}

	@Test
	fun getTeamTest_Success() = runTest {
		val expected = ApiResult.Idle
		val actual = useCase.invoke(TeamIdDomainObject(1), MatchStatus(""))
		Assert.assertEquals(expected, actual)
	}
}
