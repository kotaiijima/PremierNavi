package com.github.kota.premierNavi.di

import com.github.kota.premierNavi.data.service.TeamApiServiceImpl
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
		fun bindsFootballDataRepository(
			teamApiServiceImpl: TeamApiServiceImpl
		): TeamApiService
}