package io.github.adibfara.flappygame.ui.game.ui

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import io.github.adibfara.flappygame.ui.game.di.GameDI
import io.github.adibfara.flappygame.ui.game.di.GameDI.Companion.rememberDI
import io.github.adibfara.flappygame.ui.game.model.GameStatus
import io.github.adibfara.flappygame.ui.game.model.Viewport
import io.github.adibfara.flappygame.ui.game.ui.components.Background
import io.github.adibfara.flappygame.ui.game.ui.components.Block
import io.github.adibfara.flappygame.ui.game.ui.components.GameStartUI
import io.github.adibfara.flappygame.ui.game.ui.components.Player
import io.github.adibfara.flappygame.ui.game.ui.components.Score

@Composable
fun Game(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {

        val maxWidthPx = maxWidth.value
        val maxHeightPx = maxHeight.value

        val viewPort = remember {
            Viewport(maxWidthPx, maxHeightPx)
        }
        val di = rememberDI(viewPort)

        Box(
            Modifier
                .fillMaxSize()
                .clickable(interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null) {
                    di.playerLogic.jump()
                }) {
            Background(di.timeManager)
            Player(Modifier, di.playerLogic)
            Block(di.blockMovementLogic)
            Score(di)
            GameStartUI(di)
        }
    }
}


fun Bitmap.resizeTo(maxHeight: Int): Bitmap {
    val sourceWidth: Int = width
    val sourceHeight: Int = height
    val sourceRatio = sourceWidth.toFloat() / sourceHeight.toFloat()
    val targetWidth = (maxHeight.toFloat() * sourceRatio).toInt()
    return Bitmap.createScaledBitmap(this, targetWidth, maxHeight, true)
}

