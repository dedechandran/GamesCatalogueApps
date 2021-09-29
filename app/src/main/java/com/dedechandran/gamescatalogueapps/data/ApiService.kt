package com.dedechandran.gamescatalogueapps.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(@Query("page") page: Int): GameResponse
}