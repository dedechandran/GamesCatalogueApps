package com.dedechandran.gamescatalogueapps.domain

import androidx.paging.PagingData
import com.dedechandran.gamescatalogueapps.data.GameDataSource
import com.dedechandran.gamescatalogueapps.data.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    fun getGames(): GameDataSource {
        return gameRepository.getGames()
    }
}