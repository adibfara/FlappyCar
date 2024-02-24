package io.github.adibfara.flappygame.ui.game.engine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LogicManager(
    private val gameLogics: List<GameLogic>,
    private val timeManager: TimeManager,
    private val coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            timeManager.deltaTime.collect { deltaTime ->
                gameLogics.forEach { it.onUpdate(deltaTime) }
            }
        }
    }
}