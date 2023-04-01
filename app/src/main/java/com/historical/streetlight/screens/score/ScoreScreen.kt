package com.historical.streetlight.screens.score

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.historical.streetlight.R
import com.historical.streetlight.ui.theme.DarkGreen
import com.historical.streetlight.ui.theme.GameColor
import com.historical.streetlight.ui.theme.GradientWithGreen
import com.historical.streetlight.ui.theme.fonts
import com.historical.streetlight.utils.Contants
import com.historical.streetlight.utils.noRippleClickable

@Composable
fun ScoreScreen(
    score: Int?,
    onBackPressed: () -> Unit
) {
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        val context = LocalContext.current
        val windowManager =
            remember { context.getSystemService(Context.WINDOW_SERVICE) as WindowManager }

        val metrics = DisplayMetrics().apply {
            windowManager.defaultDisplay.getRealMetrics(this)
        }
        val (width, height) = with(LocalDensity.current) {
            Pair(metrics.widthPixels.toDp(), (metrics.heightPixels).toDp())
        }

        DialogUI(score, onBackPressed = onBackPressed, width, height)
    }
}

@Composable
fun DialogUI(
    score: Int?,
    onBackPressed: () -> Unit,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight()
            .requiredSize(width, height)
    ) {
        Image(
            painter = painterResource(id = R.drawable.score_background),
            contentDescription = "game_back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(300.dp),
                ) {
                    BoxWithConstraints(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.TopCenter)
                    ) {
                        Text(
                            text = stringResource(R.string.str_score),
                            fontSize = 36.sp,
                            fontFamily = fonts,
                            color = DarkGreen,
                            modifier = modifier.padding(top = 15.dp)
                        )
                    }

                    BoxWithConstraints(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back_big),
                            contentDescription = null,
                            modifier = modifier
                                .padding(top = 140.dp)
                                .noRippleClickable {
                                    onBackPressed()
                                }
                        )
                    }

                    BoxWithConstraints(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.TopStart)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_score_top),
                            contentDescription = null,
                            modifier = modifier.padding(top = 10.dp)
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(GradientWithGreen)
                        .padding(horizontal = 50.dp, vertical = 66.dp)
                ) {
                    Text(
                        text = (score ?: 0).toString(),
                        fontSize = 128.sp,
                        fontFamily = fonts,
                        color = GameColor
                    )
                }
            }
        }
    }
}
