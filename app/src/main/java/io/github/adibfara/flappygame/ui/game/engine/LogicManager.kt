package io.github.adibfara.flappygame.ui.game.engine

import io.github.adibfara.flappygame.ui.game.logic.GameStatusLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LogicManager(
    private val gameLogics: List<GameLogic>,
    private val gameStatusLogic: GameStatusLogic,
    private val timeManager: TimeManager,
    private val coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            timeManager.deltaTime.collect { deltaTime ->
                if (!gameStatusLogic.isStarted()) return@collect

                gameLogics.forEach { it.onUpdate(deltaTime) }
            }
        }
    }
}