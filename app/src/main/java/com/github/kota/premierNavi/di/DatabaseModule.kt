package com.github.kota.premierNavi.di

import android.content.Context
import androidx.room.Room
import com.github.kota.premierNavi.data.AppDatabase
import com.github.kota.premierNavi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext context: Context)
			= Room.databaseBuilder(
		context, AppDatabase::class.java, Constants.DATABASE_NAME
	).build()

	@Singleton
	@Provides
	fun provideDao(database: AppDatabase) = database.preNaviDao()
}