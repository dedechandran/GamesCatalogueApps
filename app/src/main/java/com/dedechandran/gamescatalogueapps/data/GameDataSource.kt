package com.dedechandran.gamescatalogueapps.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dedechandran.gamescatalogueapps.domain.Game
import javax.inject.Inject

class GameDataSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) :
    PagingSource<Int, Game>() {
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val pageNumber = params.key ?: 1
            val data = remoteDataSource.getGames(page = pageNumber).toDomain()
            LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == 1) {
                    null
                } else {
                    pageNumber - 1
                },
                nextKey = pageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}