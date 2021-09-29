package com.dedechandran.gamescatalogueapps.data

import androidx.paging.PagingData
import com.dedechandran.gamescatalogueapps.domain.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): GameDataSource
}