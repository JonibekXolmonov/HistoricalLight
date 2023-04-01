package com.historical.streetlight.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.historical.streetlight.R
import com.historical.streetlight.screens.score.ScoreScreen
import com.historical.streetlight.ui.theme.GameColor
import com.historical.streetlight.ui.theme.GradientWithGreen
import com.historical.streetlight.ui.theme.fonts
import com.historical.streetlight.utils.Contants.FIRST_LIGHT
import com.historical.streetlight.utils.Contants.SECOND_LIGHT
import com.historical.streetlight.utils.Contants.THIRD_LIGHT
import com.historical.streetlight.utils.noRippleClickable

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onVibrate: () -> Unit
) {

    val randomLightNum by remember {
        viewModel.randomLightNum
    }

    val score by remember {
        viewModel.score
    }

    val onTimeOut by remember {
        viewModel.onTimeOut
    }

    var wrongChoiceClicked by remember {
        mutableStateOf(false)
    }

    if (onTimeOut || wrongChoiceClicked) {
        ScoreScreen(score = score) {
            viewModel.randomlyTurnLightOff()
            viewModel.resetScore()
            wrongChoiceClicked = false
            onVibrate()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.game_background),
            contentDescription = "game_back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(modifier = modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()
            ) {

                Top(score, onBack = onBack)

                Middle(
                    randomLightNum = randomLightNum,
                    onTimeOut = onTimeOut,
                    turnOffRandomLight = {
                        if (it == randomLightNum) {
                            viewModel.randomlyTurnLightOff()
                            viewModel.increaseScore()
                        } else {
                            wrongChoiceClicked = true
                            viewModel.cancel()
                        }
                    },
                    onVibrate = onVibrate
                )

                Bottom()
            }
        }
    }
}

@Composable
fun Middle(
    randomLightNum: Int,
    turnOffRandomLight: (Int) -> Unit,
    onTimeOut: Boolean = false,
    onVibrate: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        ConstraintLayout {
            val (light1, light2, light3) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_street_light),
                contentDescription = "light",
                modifier = modifier
                    .padding(top = 100.dp)
                    .size(width = 200.dp, height = 400.dp),
                contentScale = ContentScale.Crop
            )

            Image(painterResource(id = if (randomLightNum == FIRST_LIGHT) R.drawable.ic_game_off_light else R.drawable.ic_game_on_light),
                contentDescription = null,
                modifier = modifier
                    .constrainAs(light1) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top, margin = 72.dp)
                    }
                    .noRippleClickable {
                        if (!onTimeOut) {
                            turnOffRandomLight(FIRST_LIGHT)
                            onVibrate()
                        }
                    })

            Image(painterResource(id = if (randomLightNum == SECOND_LIGHT) R.drawable.ic_game_off_light else R.drawable.ic_game_on_light),
                contentDescription = null,
                modifier = modifier
                    .constrainAs(light2) {
                        top.linkTo(parent.top, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .noRippleClickable {
                        if (!onTimeOut) {
                            turnOffRandomLight(SECOND_LIGHT)
                            onVibrate()
                        }
                    })

            Image(painterResource(id = if (randomLightNum == THIRD_LIGHT) R.drawable.ic_game_off_light else R.drawable.ic_game_on_light),
                contentDescription = null,
                modifier = modifier
                    .constrainAs(light3) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 70.dp)
                    }
                    .noRippleClickable {
                        if (!onTimeOut) {
                            turnOffRandomLight(THIRD_LIGHT)
                            onVibrate()
                        }
                    })
        }
    }
}

@Composable
fun Top(
    score: Int, modifier: Modifier = Modifier, onBack: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 48.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Image(painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            modifier = modifier.noRippleClickable { onBack() })

        Text(
            text = score.toString(),
            fontSize = 48.sp,
            fontFamily = fonts,
            color = GameColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(GradientWithGreen)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun Bottom(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier.fillMaxWidth()
    ) {

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_man_with_wife),
                contentDescription = "man",
                modifier = modifier.padding(start = 4.dp, bottom = 34.dp)
            )
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_carriage),
                contentDescription = "carriage",
                modifier = modifier.padding(end = 4.dp, top = 34.dp)
            )
        }
    }
}