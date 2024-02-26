package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.engine.GameLogic
import io.github.adibfara.flappygame.ui.game.model.GameStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameStatusLogic(coroutineScope: CoroutineScope) : GameLogic {
    private val _gameState = MutableStateFlow(GameStatus.NotStarted)
    val gameState: StateFlow<GameStatus> = _gameState

    init {
        coroutineScope.launch {
            gameState.mapLatest {
                if (it == GameStatus.GameOver) {
                    delay(3000)
                    restartGame()
                }
            }.collect()
        }
    }
    override fun onUpdate(deltaTime: Float) {

    }

    fun gameStarted() {
        _gameState.update { GameStatus.Started }
    }

    fun restartGame() {
        _gameState.update { GameStatus.NotStarted }
    }

    fun isStarted(): Boolean {
        return _gameState.value == GameStatus.Started
    }

    fun gameOver() {
        _gameState.update { GameStatus.GameOver }
    }
}