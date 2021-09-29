package com.dedechandran.gamescatalogueapps.data

import com.dedechandran.gamescatalogueapps.domain.Game
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("results") val results: List<Game>
){
    data class Game(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String?,
        @SerializedName("background_image") val image: String?,
        @SerializedName("released") val releaseDate: String?
    )
}

fun GameResponse.toDomain() : List<Game> = results.map {
    Game(
        id = it.id,
        image = it.image.orEmpty(),
        name = it.name.orEmpty(),
        releaseDate = it.releaseDate.orEmpty()
    )
}