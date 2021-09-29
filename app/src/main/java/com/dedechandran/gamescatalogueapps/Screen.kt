package com.dedechandran.gamescatalogueapps

sealed class Screen(val route: String){
    object GamesScreen: Screen(route = "games")
    object GameDetailsScreen: Screen(route = "gameDetails/{id}"){
        fun createRoute(id: String) = "gameDetails/$id"
    }
}
