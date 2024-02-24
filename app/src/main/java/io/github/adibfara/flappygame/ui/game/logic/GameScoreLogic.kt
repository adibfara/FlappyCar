package io.github.adibfara.flappygame.ui.game.logic

import androidx.compose.runtime.mutableStateOf
import io.github.adibfara.flappygame.ui.game.engine.GameLogic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameScoreLogic(
    private val playerLogic: PlayerLogic,
    private val blockLogic: BlockLogic
) : GameLogic {
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score
    override fun onUpdate(deltaTime: Float) {
        val scoreRect = blockLogic.blockPosition.value.scoreRect
        val playerRect = playerLogic.player.value.rect

        val hasScored = playerRect.overlaps(scoreRect)
        if (hasScored) {
            _score.update { it + 1 }
        }
    }
}