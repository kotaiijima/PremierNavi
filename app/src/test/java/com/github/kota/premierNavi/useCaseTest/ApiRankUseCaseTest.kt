package com.github.kota.premierNavi.useCaseTest

import com.github.kota.premierNavi.fake.FakeRankApiService
import com.github.kota.premierNavi.usecase.RankUseCase
import com.github.kota.premierNavi.utils.ApiResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiRankUseCaseTest {

	private lateinit var useCase: RankUseCase
	private val fakeRankApiService = FakeRankApiService()

	@Before
	fun setUp() {
		useCase = RankUseCase(fakeRankApiService)
	}

	@Test
	fun getTeamTest_Success() = runTest {
		val expected = ApiResult.Idle
		val actual = useCase.invoke()
		Assert.assertEquals(expected, actual)
	}
}
