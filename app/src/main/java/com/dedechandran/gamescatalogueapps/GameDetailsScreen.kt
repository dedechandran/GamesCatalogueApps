package com.dedechandran.gamescatalogueapps

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun GameDetailsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            GameDetailsAppBar()
        },
    ) {
        GameDetailsContent()
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun GameDetailsContent(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.spacing_2))
            .verticalScroll(scrollState)
    ) {
        GameDetailsHeader()
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_4)))
        GameDescription()
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_4)))
        GamePlatform()
    }
}

@Composable
fun GameDetailsAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = "Game Details",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier
                    .wrapContentSize()
            ) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "")
            }
        },
    )
}


@Composable
fun GameDetailsHeader(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.large
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            val (imageRef, titleRef, subTitleRef) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = modifier
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Game",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(titleRef) {
                        bottom.linkTo(subTitleRef.top)
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Start
            )
            Text(
                text = "Game",
                style = MaterialTheme.typography.subtitle1,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(subTitleRef) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        start.linkTo(titleRef.start)
                        end.linkTo(titleRef.end)
                    },
                textAlign = TextAlign.Start
            )

        }

    }
}


@ExperimentalAnimationApi
@Composable
fun GameDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_2)))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse orci lorem, cursus fermentum accumsan eget, hendrerit varius dolor. Nam vitae rutrum libero. Donec lacinia, nibh eget suscipit consectetur, velit lorem pellentesque ex, vel sodales risus nunc sed tortor. In vel bibendum odio. Duis nec sapien bibendum, luctus est commodo, vulputate nisi. In hac habitasse platea dictumst. Vivamus in libero placerat, tempor eros in, consectetur diam. Nunc sagittis consectetur neque nec pretium. Mauris accumsan, lorem non posuere euismod, mauris mi ultricies purus, feugiat tempus nunc mauris pharetra libero. Aliquam ac dapibus erat. Nulla vel quam semper lorem efficitur sollicitudin. Etiam in est a libero iaculis facilisis. Sed interdum metus et faucibus pretium.",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Justify,
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun GamePlatform(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Text(
            text = "Platforms",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.spacing_2)))
        GamePlatformDetailList(gamePlatformDetails = listOf(1, 2, 3, 4, 5, 6))
    }
}

@ExperimentalMaterialApi
@Composable
fun GamePlatformDetailList(gamePlatformDetails: List<Int>) {
    LazyRow() {
        items(gamePlatformDetails) {
            GamePlatformDetailItem()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun GamePlatformDetailItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = dimensionResource(id = R.dimen.spacing_2)),
        shape = MaterialTheme.shapes.large,
        onClick = {

        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.spacing_2)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(shape = MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = "Game",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun GameDetailsPreview() {
    GameDetailsScreen()
}

