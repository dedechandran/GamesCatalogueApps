package com.dedechandran.gamescatalogueapps.di

import com.dedechandran.gamescatalogueapps.data.GameRepositoryImpl
import com.dedechandran.gamescatalogueapps.data.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGameRepository(gameRepository: GameRepositoryImpl): GameRepository {
        return gameRepository
    }
}