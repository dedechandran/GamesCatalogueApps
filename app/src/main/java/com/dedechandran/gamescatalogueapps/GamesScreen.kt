package com.dedechandran.gamescatalogueapps

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dedechandran.gamescatalogueapps.domain.Game
import kotlinx.coroutines.flow.Flow

@ExperimentalMaterialApi
@Composable
fun GamesScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    gamesViewModel: GamesViewModel
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Games Catalogue",
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                }
            )
        },
    ) {
        GamesContent(onItemClicked = onItemClicked, gamesList = gamesViewModel.getAllGames())
    }
}

@ExperimentalMaterialApi
@Composable
fun GamesContent(
    modifier: Modifier = Modifier,
    gamesList: Flow<PagingData<Game>>,
    onItemClicked: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.spacing_4),
                end = dimensionResource(id = R.dimen.spacing_4),
                top = dimensionResource(id = R.dimen.spacing_4),
            )
            .fillMaxSize()
    ) {
        GamesPlatform()
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_2)))
        GamesList(
            games = listOf(1, 2, 3, 4, 5, 6, 7),
            gamesList = gamesList,
            onItemClicked = onItemClicked
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun GamesPlatform(modifier: Modifier = Modifier) {
    var selectedPlatform by remember {
        mutableStateOf(0)
    }
    GamesPlatformList(
        platforms = listOf("PC", "Playstation", "Xbox", "Switch", "Mobile"),
        selectedPlatformIndex = selectedPlatform,
        onSelectedPlatform = { selectedPlatform = it },
    )
}

@ExperimentalMaterialApi
@Composable
fun GamesPlatformList(
    modifier: Modifier = Modifier,
    platforms: List<String>,
    selectedPlatformIndex: Int,
    onSelectedPlatform: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(scrollState)
            .selectableGroup()
    ) {
        platforms.forEachIndexed { index, value ->
            val isSelected = index == selectedPlatformIndex
            val elevation =
                if (isSelected) dimensionResource(id = R.dimen.elevation_4) else dimensionResource(
                    id = R.dimen.elevation_1
                )
            val color = if (isSelected) Color.Yellow else Color.White
            GamesPlatformItem(
                platform = value,
                elevation = elevation,
                color = color,
                onItemSelected = { onSelectedPlatform.invoke(index) },
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun GamesPlatformItem(
    modifier: Modifier = Modifier,
    platform: String,
    elevation: Dp,
    color: Color,
    onItemSelected: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(end = dimensionResource(id = R.dimen.spacing_2)),
        elevation = elevation,
        backgroundColor = color,
        shape = MaterialTheme.shapes.medium,
        onClick = {
            onItemSelected.invoke()
        },
    ) {
        Text(
            text = platform,
            modifier = modifier.padding(dimensionResource(id = R.dimen.spacing_2))
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun GamesList(
    modifier: Modifier = Modifier,
    games: List<Int>,
    gamesList: Flow<PagingData<Game>>,
    onItemClicked: (String) -> Unit
) {
    val lazyGamesList = gamesList.collectAsLazyPagingItems()
    LazyColumn() {
        items(lazyGamesList){ game ->
            game?.let {
                GamesItem(onItemClicked = onItemClicked, game = it)
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun GamesItem(modifier: Modifier = Modifier, game: Game, onItemClicked : (String) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = dimensionResource(id = R.dimen.spacing_2)),
        shape = MaterialTheme.shapes.large,
        onClick = { onItemClicked.invoke("1") }
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.spacing_2))
        ) {
            val (imageRef, titleRef, subTitleRef) = createRefs()
            Image(
                painter = rememberImagePainter(
                    data = game.image,
                    builder = {
                        crossfade(enable = true)
                    }
                ),
                contentDescription = "",
                modifier = modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .constrainAs(imageRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .clip(shape = MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = game.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(titleRef) {
                        top.linkTo(imageRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(
                        top = dimensionResource(id = R.dimen.spacing_2),
                        start = dimensionResource(id = R.dimen.spacing_2),
                        end = dimensionResource(id = R.dimen.spacing_2),
                    ),
                textAlign = TextAlign.Start
            )
            Text(
                text = game.releaseDate,
                style = MaterialTheme.typography.subtitle2,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(subTitleRef) {
                        top.linkTo(titleRef.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(titleRef.start)
                        end.linkTo(titleRef.end)
                    }
                    .padding(
                        start = dimensionResource(id = R.dimen.spacing_2),
                        end = dimensionResource(id = R.dimen.spacing_2),
                    ),
                textAlign = TextAlign.Start
            )
        }
    }
}
