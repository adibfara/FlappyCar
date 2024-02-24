package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.engine.GameLogic
import io.github.adibfara.flappygame.ui.game.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerLogic : GameLogic {
    private val _playerPosition = MutableStateFlow(Player(100f, 0f))
    val player: StateFlow<Player> = _playerPosition


    override fun onUpdate(deltaTime: Float) {
        _playerPosition.update { player ->
            var newY =
                player.y + player.speed * deltaTime + 0.5 * Player.acceleration * deltaTime * deltaTime
            var newSpeed = player.speed + Player.acceleration * deltaTime

            if (newY > 2000f) {
                newY = 0.0
                newSpeed = 0f
            }

            player.copy(y = newY.toFloat(), speed = newSpeed)
        }
    }

    fun jump() {
        _playerPosition.update {
            it.copy(speed = -0.25f)
        }
    }


}