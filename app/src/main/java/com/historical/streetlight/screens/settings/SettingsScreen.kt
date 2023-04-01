package com.historical.streetlight.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.historical.streetlight.R
import com.historical.streetlight.ui.theme.GameColor
import com.historical.streetlight.ui.theme.Gradient
import com.historical.streetlight.ui.theme.fonts
import com.historical.streetlight.utils.noRippleClickable

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onVibrate: () -> Unit,
    onSound: (Boolean) -> Unit
) {

    val vibrationEnabled by remember {
        viewModel.vibrationEnabled
    }

    val soundEnabled by remember {
        viewModel.soundEnabled
    }

    ScreenUI(
        vibrationEnabled,
        soundEnabled,
        onVibrationChange = {
            viewModel.controlVibration(it)
            onVibrate()
        },
        onSoundChange = {
            viewModel.controlSound(it)
            onVibrate()
            onSound(soundEnabled)
        },
        onBackPressed = {
            onBackPressed()
            onVibrate()
        }
    )
}

@Composable
fun ScreenUI(
    vibrationEnabled: Boolean,
    soundEnabled: Boolean,
    onVibrationChange: (Boolean) -> Unit,
    onSoundChange: (Boolean) -> Unit,
    onBackPressed: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.settings_background),
            contentDescription = "settings_back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 44.dp)
        ) {

            Image(painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier.noRippleClickable { onBackPressed() })


            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.str_sound
                    ),
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = fonts,
                    color = GameColor,
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Gradient)
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                )

                Image(
                    painter = painterResource(id = if (soundEnabled) R.drawable.ic_on_light else R.drawable.ic_off_light),
                    contentDescription = stringResource(
                        id = R.string.str_sound
                    ),
                    modifier = Modifier
                        .padding(end = 27.dp)
                        .noRippleClickable {
                            onSoundChange(!soundEnabled)
                        }
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.str_vibration
                    ),
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    fontFamily = fonts,
                    color = GameColor,
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Gradient)
                        .padding(horizontal = 8.dp)
                        .weight(1f)

                )

                Image(
                    painter = painterResource(id = if (vibrationEnabled) R.drawable.ic_on_light else R.drawable.ic_off_light),
                    contentDescription = stringResource(
                        id = R.string.str_vibration
                    ),
                    modifier = Modifier
                        .padding(end = 27.dp)
                        .noRippleClickable {
                            onVibrationChange(!vibrationEnabled)
                        }
                )
            }

        }
    }
}