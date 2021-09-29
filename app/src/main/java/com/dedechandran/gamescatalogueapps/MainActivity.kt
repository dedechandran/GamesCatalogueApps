package com.dedechandran.gamescatalogueapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dedechandran.gamescatalogueapps.ui.theme.GamesCatalogueAppsTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            GamesCatalogueAppsTheme {
                Scaffold { innerPadding ->
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.GamesScreen.route
                    ) {
                        composable(
                            route = Screen.GamesScreen.route,
                            enterTransition = { _, _ ->
                                slideInHorizontally(
                                    initialOffsetX = { -1000 },
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = { _, _ ->
                                slideOutHorizontally(
                                    targetOffsetX = { -1000 },
                                    animationSpec = tween(700)
                                )
                            },
                        ) {
                            GamesScreen(
                                modifier = Modifier.padding(innerPadding),
                                onItemClicked = {
                                    navController.navigate(Screen.GameDetailsScreen.createRoute(it))
                                },
                                gamesViewModel = hiltViewModel(),
                            )
                        }
                        composable(
                            route = Screen.GameDetailsScreen.route,
                            enterTransition = { _, _ ->
                                slideInHorizontally(
                                    initialOffsetX = { 1000 },
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = { _, _ ->
                                slideOutHorizontally(
                                    targetOffsetX = { 1000 },
                                    animationSpec = tween(700)
                                )
                            },
                        ) {
                            GameDetailsScreen(modifier = Modifier.padding(innerPadding), onBackPressed = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GamesCatalogueAppsTheme {
    }
}