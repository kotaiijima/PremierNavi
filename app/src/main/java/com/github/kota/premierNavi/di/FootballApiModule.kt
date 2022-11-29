package com.github.kota.premierNavi.di

import com.github.kota.premierNavi.data.api.MatchApi
import com.github.kota.premierNavi.data.api.RankApi
import com.github.kota.premierNavi.data.api.StatsApi
import com.github.kota.premierNavi.data.api.TeamApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FootballApiModule {

	@Provides
	@Singleton
	fun provideMatchApi(builder:Retrofit.Builder): MatchApi{
		return builder
			.build()
			.create(MatchApi::class.java)
	}

	@Provides
	@Singleton
	fun provideTeamApi(builder:Retrofit.Builder): TeamApi{
		return builder
			.build()
			.create(TeamApi::class.java)
	}

	@Provides
	@Singleton
	fun provideRankApi(builder:Retrofit.Builder): RankApi{
		return builder
			.build()
			.create(RankApi::class.java)
	}

	@Provides
	@Singleton
	fun provideStatsApi(builder:Retrofit.Builder): StatsApi{
		return builder
			.build()
			.create(StatsApi::class.java)
	}

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit.Builder{
		return Retrofit.Builder()
			.baseUrl("https://api.football-data.org")
			.addConverterFactory(MoshiConverterFactory.create())
	}
}