package com.github.kota.premierNavi.useCaseTest

import com.github.kota.premierNavi.domain.TeamIdDomainObject
import com.github.kota.premierNavi.fake.FakeStatsApiService
import com.github.kota.premierNavi.usecase.StatsUseCase
import com.github.kota.premierNavi.utils.ApiResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiStatsUseCaseTest {

	private lateinit var useCase: StatsUseCase
	private val fakeStatsApiService = FakeStatsApiService()

	@Before
	fun setUp() {
		useCase = StatsUseCase(fakeStatsApiService)
	}

	@Test
	fun getStatsTest_Success() = runTest {
		val expected = ApiResult.Idle
		val actual = useCase.invoke(TeamIdDomainObject(1))
		Assert.assertEquals(expected, actual)
	}
}
