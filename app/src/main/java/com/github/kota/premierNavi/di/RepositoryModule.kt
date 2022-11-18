package com.github.kota.premierNavi.di

import com.github.kota.premierNavi.data.repository.FootballDataRepositoryImpl
import com.github.kota.premierNavi.domain.FootballDataRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
  @Binds
  @Singleton
  fun bindsFootballDataRepository(
    footballDataRepositoryImpl: FootballDataRepositoryImpl
  ): FootballDataRepository
}
