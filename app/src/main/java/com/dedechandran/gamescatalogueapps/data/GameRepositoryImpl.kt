package com.dedechandran.gamescatalogueapps.data

import com.dedechandran.gamescatalogueapps.data.GameDataSource
import com.dedechandran.gamescatalogueapps.data.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameDataSource: GameDataSource
) : GameRepository {
    override fun getGames(): GameDataSource {
        return gameDataSource
    }
}