package com.github.kota.premierNavi.di

import com.github.kota.premierNavi.data.api.MatchApi
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
	fun provideLatestGameApi(builder:Retrofit.Builder): MatchApi{
		return builder
			.build()
			.create(MatchApi::class.java)
	}

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit.Builder{
		return Retrofit.Builder()
			.baseUrl("https://api.football-data.org")
			.addConverterFactory(MoshiConverterFactory.create())
	}
}