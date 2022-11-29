package com.github.kota.premierNavi.di

import com.github.kota.premierNavi.data.service.MatchApiServiceImpl
import com.github.kota.premierNavi.data.service.RankApiServiceImpl
import com.github.kota.premierNavi.data.service.StatsApiServiceImpl
import com.github.kota.premierNavi.data.service.TeamApiServiceImpl
import com.github.kota.premierNavi.domain.service.MatchApiService
import com.github.kota.premierNavi.domain.service.RankApiService
import com.github.kota.premierNavi.domain.service.StatsApiService
import com.github.kota.premierNavi.domain.service.TeamApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {
		@Binds
		@Singleton
		fun bindsTeamApiService(
			teamApiServiceImpl: TeamApiServiceImpl
		): TeamApiService

		@Binds
		@Singleton
		fun bindsRankApiService(
			rankApiServiceImpl: RankApiServiceImpl
		): RankApiService

		@Binds
		@Singleton
		fun bindsStatsApiService(
			statsApiServiceImpl: StatsApiServiceImpl
		): StatsApiService

		@Binds
		@Singleton
		fun bindsMatchApiService(
			matchApiServiceImpl: MatchApiServiceImpl
		): MatchApiService
}