package com.github.kota.premierNavi.di

import com.github.kota.premierNavi.data.repository.FootballDataRepositoryImpl
import com.github.kota.premierNavi.data.repository.TeamRepositoryImpl
import com.github.kota.premierNavi.domain.repository.FootballDataRepository
import com.github.kota.premierNavi.domain.repository.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
  @Binds
  @Singleton
  fun bindsFootballDataRepository(
    footballDataRepositoryImpl: FootballDataRepositoryImpl
  ): FootballDataRepository

  @Binds
  @Singleton
  fun bindsTeamRepository(
    teamRepositoryImpl: TeamRepositoryImpl
  ): TeamRepository
}
