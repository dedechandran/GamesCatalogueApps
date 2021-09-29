package com.dedechandran.gamescatalogueapps.di

import com.dedechandran.gamescatalogueapps.data.RemoteDataSource
import com.dedechandran.gamescatalogueapps.data.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource{
        return remoteDataSource
    }

}