package io.github.adibfara.flappygame.ui.game.ui

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import io.github.adibfara.flappygame.R
import io.github.adibfara.flappygame.ui.game.engine.LogicManager
import io.github.adibfara.flappygame.ui.game.engine.gameCoroutineScope
import io.github.adibfara.flappygame.ui.game.logic.BlockMovementLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.engine.TimeManager
import io.github.adibfara.flappygame.ui.game.engine.compose.toPx
import io.github.adibfara.flappygame.ui.game.logic.BlockCreatorLogic
import io.github.adibfara.flappygame.ui.game.logic.GameOverManager
import io.github.adibfara.flappygame.ui.game.logic.GameScoreLogic
import io.github.adibfara.flappygame.ui.game.logic.GameStatusLogic
import io.github.adibfara.flappygame.ui.game.logic.OnGameOverLogic
import io.github.adibfara.flappygame.ui.game.model.Viewport
import io.github.adibfara.flappygame.ui.game.ui.components.Background
import io.github.adibfara.flappygame.ui.game.ui.components.Block
import io.github.adibfara.flappygame.ui.game.ui.components.Player
import kotlinx.coroutines.delay

@Composable
fun Game(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {

        val maxWidthPx = maxWidth.value
        val maxHeightPx = maxHeight.value

        val viewPort = remember {
            Viewport(maxWidthPx, maxHeightPx)
        }

        val timeManager = remember {
            TimeManager()
        }

        val gameStatusLogic = remember {
            GameStatusLogic()
        }

        val coroutineScope = remember {
            gameCoroutineScope()
        }
        val playerLogic = remember {
            PlayerLogic(gameStatusLogic)
        }

        val blockMovementLogic = remember {
            BlockMovementLogic(viewPort)
        }
        val playerCollisionLogic = remember {
            PlayerCollisionLogic(playerLogic, blockMovementLogic, gameStatusLogic)
        }

        val gameScoreLogic = remember {
            GameScoreLogic(playerLogic, blockMovementLogic)
        }

        val gameOverManager = remember {
            val onGameOverLogics: List<OnGameOverLogic> =
                listOf(playerLogic, blockMovementLogic, gameScoreLogic)
            GameOverManager(gameStatusLogic, onGameOverLogics, coroutineScope)
        }
        val logicManager = remember {
            val logics = listOf(
                playerLogic,
                blockMovementLogic,
                playerCollisionLogic,
                gameScoreLogic,
                gameStatusLogic,
            )
            LogicManager(logics, gameStatusLogic, timeManager, coroutineScope)
        }

        val blockCreatorLogic = remember {
            BlockCreatorLogic(blockMovementLogic, coroutineScope, viewPort)
        }
        Box(
            Modifier
                .fillMaxSize()
                .clickable {
                    playerLogic.jump()
                }) {
            Background(timeManager)
            Player(Modifier, playerLogic, playerCollisionLogic)
            Block(blockMovementLogic)
            Text(
                gameScoreLogic.score.collectAsState().value.toString(),
                Modifier.align(Alignment.TopEnd),
                color = Color.White
            )
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

