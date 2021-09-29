package com.dedechandran.gamescatalogueapps.data

interface RemoteDataSource {
    suspend fun getGames(page: Int): GameResponse
}