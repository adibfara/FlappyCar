package io.github.adibfara.flappygame.ui.game.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import io.github.adibfara.flappygame.ui.game.engine.LogicManager
import io.github.adibfara.flappygame.ui.game.engine.TimeManager
import io.github.adibfara.flappygame.ui.game.engine.gameCoroutineScope
import io.github.adibfara.flappygame.ui.game.logic.BlockSpawnerLogic
import io.github.adibfara.flappygame.ui.game.logic.BlockMovementLogic
import io.github.adibfara.flappygame.ui.game.logic.GameOverManager
import io.github.adibfara.flappygame.ui.game.logic.GameScoreLogic
import io.github.adibfara.flappygame.ui.game.logic.GameStatusLogic
import io.github.adibfara.flappygame.ui.game.logic.OnGameOverLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.model.Viewport

class GameDI(viewport: Viewport, val timeManager: TimeManager) {
    val coroutineScope = gameCoroutineScope()
    val gameStatusLogic = GameStatusLogic(coroutineScope)
    val playerLogic = PlayerLogic(gameStatusLogic, viewport)
    val blockMovementLogic = BlockMovementLogic(viewport)
    val playerCollisionLogic =
        PlayerCollisionLogic(playerLogic, blockMovementLogic, gameStatusLogic, viewport)
    val gameScoreLogic = GameScoreLogic(playerLogic, blockMovementLogic)
    private val onGameOverLogics: List<OnGameOverLogic> =
        listOf(playerLogic, blockMovementLogic, gameScoreLogic)
    val gameOverManager = GameOverManager(gameStatusLogic, onGameOverLogics, coroutineScope)
    private val logics = listOf(
        playerLogic,
        blockMovementLogic,
        playerCollisionLogic,
        gameScoreLogic,
        gameStatusLogic,
    )
    val logicManager = LogicManager(logics, gameStatusLogic, timeManager, coroutineScope)

    val blockSpawnerLogic =
        BlockSpawnerLogic(blockMovementLogic, gameStatusLogic, coroutineScope, viewport)

    companion object {
        @Composable
        fun rememberDI(viewport: Viewport): GameDI {
            val coroutineScope = rememberCoroutineScope()
            val timeManager = remember {
                TimeManager(coroutineScope)
            }
            return remember {
                GameDI(viewport, timeManager)
            }
        }
    }
}