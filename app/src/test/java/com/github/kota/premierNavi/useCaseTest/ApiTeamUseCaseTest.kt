package com.github.kota.premierNavi.useCaseTest

import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.fake.FakeTeamApiService
import com.github.kota.premierNavi.usecase.TeamUseCase
import com.github.kota.premierNavi.utils.ApiResult
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
	fun getTeamTest_Success() = runTest {
		val expected = ApiResult.Idle
		val actual = useCase.invoke(TeamIdDomainObject(1))
		Assert.assertEquals(expected, actual)
	}
}
