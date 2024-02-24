package io.github.adibfara.flappygame.ui.game.ui

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import io.github.adibfara.flappygame.ui.game.engine.LogicManager
import io.github.adibfara.flappygame.ui.game.engine.gameCoroutineScope
import io.github.adibfara.flappygame.ui.game.logic.BlockLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.engine.TimeManager
import io.github.adibfara.flappygame.ui.game.engine.compose.toPx
import io.github.adibfara.flappygame.ui.game.logic.GameOverManager
import io.github.adibfara.flappygame.ui.game.logic.GameScoreLogic
import io.github.adibfara.flappygame.ui.game.logic.GameStatusLogic
import io.github.adibfara.flappygame.ui.game.logic.OnGameOverLogic
import io.github.adibfara.flappygame.ui.game.model.Viewport
import io.github.adibfara.flappygame.ui.game.ui.components.Block
import io.github.adibfara.flappygame.ui.game.ui.components.Player

@Composable
fun Game(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {

        val maxWidthPx = maxWidth.toPx()
        val maxHeightPx = maxHeight.toPx()

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

        val blockLogic = remember {
            BlockLogic(viewPort)
        }
        val playerCollisionLogic = remember {
            PlayerCollisionLogic(playerLogic, blockLogic, gameStatusLogic)
        }

        val gameScoreLogic = remember {
            GameScoreLogic(playerLogic, blockLogic)
        }

        val gameOverManager = remember {
            val onGameOverLogics: List<OnGameOverLogic> =
                listOf(playerLogic, blockLogic, gameScoreLogic)
            GameOverManager(gameStatusLogic, onGameOverLogics, coroutineScope)
        }
        val logicManager = remember {
            val logics = listOf(
                playerLogic,
                blockLogic,
                playerCollisionLogic,
                gameScoreLogic,
                gameStatusLogic,
            )
            LogicManager(logics, gameStatusLogic, timeManager, coroutineScope)
        }
        Box(
            Modifier
                .fillMaxSize()
                .clickable {
                    playerLogic.jump()
                }) {
            Player(Modifier, playerLogic, playerCollisionLogic)
            Block(blockLogic)
            Text(
                gameScoreLogic.score.collectAsState().value.toString(),
                Modifier.align(Alignment.TopEnd)
            )
            Text(gameStatusLogic.gameState.collectAsState().value.toString())
        }
    }
}




