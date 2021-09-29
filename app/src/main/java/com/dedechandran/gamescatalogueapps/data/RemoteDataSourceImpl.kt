package com.dedechandran.gamescatalogueapps.data

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
) : RemoteDataSource {
    override suspend fun getGames(page: Int): GameResponse {
        return service.getGames(page = page)
    }
}