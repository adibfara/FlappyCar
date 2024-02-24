package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.model.GameStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class GameOverManager(
    private val gameStatusLogic: GameStatusLogic,
    private val onGameOverLogics: List<OnGameOverLogic>,
    private val coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            gameStatusLogic.gameState.filter {
                it == GameStatus.GameOver
            }.collect {
                onGameOverLogics.forEach { it.onGameOver() }
            }
        }
    }
}