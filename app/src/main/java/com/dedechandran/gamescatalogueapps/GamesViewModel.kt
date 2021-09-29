package com.dedechandran.gamescatalogueapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dedechandran.gamescatalogueapps.domain.Game
import com.dedechandran.gamescatalogueapps.domain.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
): ViewModel() {

    fun getAllGames(): Flow<PagingData<Game>>{
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true),
            pagingSourceFactory = {
                getGamesUseCase.getGames()
            }
        ).flow
    }

}