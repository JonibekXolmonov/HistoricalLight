package com.historical.streetlight.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.historical.streetlight.R
import com.historical.streetlight.ui.theme.GameColor
import com.historical.streetlight.ui.theme.Gradient
import com.historical.streetlight.ui.theme.fonts
import com.historical.streetlight.utils.noRippleClickable

@Composable
fun MenuScreen(
    onGameStart: () -> Unit,
    onSettingsClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = "menu_back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "settings",
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = 48.dp, end = 16.dp)
                        .noRippleClickable { onSettingsClick() }
                )
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomCenter)
            ) {

                Button(
                    onClick = onGameStart,
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 40.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Gradient),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = GameColor,
                        backgroundColor = Color.Transparent
                    ),
                    elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.str_game),
                        fontSize = 64.sp,
                        color = GameColor,
                        fontFamily = fonts
                    )
                }
            }
        }
    }
}