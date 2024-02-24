package io.github.adibfara.flappygame.ui.game.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.adibfara.flappygame.ui.game.engine.LogicManager
import io.github.adibfara.flappygame.ui.game.engine.TimeManager
import io.github.adibfara.flappygame.ui.game.engine.gameCoroutineScope
import io.github.adibfara.flappygame.ui.game.logic.BlockCreatorLogic
import io.github.adibfara.flappygame.ui.game.logic.BlockMovementLogic
import io.github.adibfara.flappygame.ui.game.logic.GameOverManager
import io.github.adibfara.flappygame.ui.game.logic.GameScoreLogic
import io.github.adibfara.flappygame.ui.game.logic.GameStatusLogic
import io.github.adibfara.flappygame.ui.game.logic.OnGameOverLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.model.Viewport

class GameDI(private val viewport: Viewport) {
    val timeManager = TimeManager()
    val gameStatusLogic = GameStatusLogic()
    val coroutineScope = gameCoroutineScope()
    val playerLogic = PlayerLogic(gameStatusLogic)
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

    val blockCreatorLogic = BlockCreatorLogic(blockMovementLogic, coroutineScope, viewport)

    companion object {
        @Composable
        fun rememberDI(viewport: Viewport): GameDI {
            return remember {
                GameDI(viewport)
            }
        }
    }
}