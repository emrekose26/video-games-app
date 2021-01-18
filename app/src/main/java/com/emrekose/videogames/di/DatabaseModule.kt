package com.emrekose.videogames.di

import android.content.Context
import androidx.room.Room
import com.emrekose.videogames.data.local.AppDatabase
import com.emrekose.videogames.data.local.FavGamesDao
import com.emrekose.videogames.data.local.GamesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext application: Context): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "games.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideGamesDao(appDatabase: AppDatabase): GamesDao = appDatabase.gamesDao()

    @Provides
    @Singleton
    fun provideFavGamesDao(appDatabase: AppDatabase): FavGamesDao = appDatabase.favGamesDao()

}