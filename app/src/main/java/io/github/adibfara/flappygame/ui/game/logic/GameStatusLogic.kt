package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.engine.GameLogic
import io.github.adibfara.flappygame.ui.game.model.GameStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameStatusLogic : GameLogic {
    private val _gameState = MutableStateFlow(GameStatus.NotStarted)
    val gameState: StateFlow<GameStatus> = _gameState
    override fun onUpdate(deltaTime: Float) {

    }

    fun gameStarted() {
        _gameState.update { GameStatus.Started }
    }

    fun isStarted(): Boolean {
        return _gameState.value == GameStatus.Started
    }

    fun gameOver() {
        _gameState.update { GameStatus.GameOver }
    }
}