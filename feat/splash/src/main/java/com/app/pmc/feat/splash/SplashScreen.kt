package com.app.pmc.feat.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pmc.core.ui.R.drawable
import com.app.pmc.core.ui.R.string
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.surface.GradientSurface
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToLoginScreen: () -> Unit = {},
    onNavigateToJoinScreen: () -> Unit = {}
) {
    GradientSurface {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GetContent(
                modifier = modifier,
                onNavigateToJoinScreen = onNavigateToLoginScreen,
            )
        }
    }
}

@Composable
private fun GetContent(
    modifier: Modifier = Modifier,
    onNavigateToJoinScreen: () -> Unit = {}
) {
    val contentList = listOf(
        null to drawable.ic_app_name,
        string.onboarding_description_1 to drawable.image_onboarding_graphy_1,
        string.onboarding_description_2 to drawable.image_onboarding_graphy_2,
        string.onboarding_description_3 to drawable.image_onboarding_graphy_3,
    )

    var contentNum by remember { mutableIntStateOf(-1) }
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        for (index in contentList.indices) {
            contentNum = index
            isVisible = true
            delay(2000L)
            if (index != contentList.lastIndex) {
                isVisible = false
                delay(800L)
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        contentList.getOrNull(contentNum)?.let { (description, painter) ->
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(initialAlpha = 0.1f),
                exit = if (contentNum == contentList.lastIndex) fadeOut(targetAlpha = 1f) else fadeOut(
                    targetAlpha = 0.1f
                )
            ) {
                SplashScreenContent(
                    description = description,
                    painter = painterResource(id = painter)
                )
            }
        }
        if (contentNum == contentList.lastIndex) EchogButton(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(20.dp),
            label = stringResource(id = string.onboarding_start_description),
            onClick = {
                onNavigateToJoinScreen()
            }
        )
    }
}

@Composable
private fun SplashScreenContent(
    description: Int?,
    painter: Painter
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = null
        )
        description?.let {
            Text(
                fontWeight = FontWeight.W600,
                fontSize = 22.sp,
                text = stringResource(id = it),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    SplashScreen()
}