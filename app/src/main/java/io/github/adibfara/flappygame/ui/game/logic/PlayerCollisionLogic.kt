package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.model.Block
import io.github.adibfara.flappygame.ui.game.model.Pipe
import io.github.adibfara.flappygame.ui.game.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerCollisionLogic(
    private val playerLogic: PlayerLogic,
    private val blockLogic: BlockLogic,
    private val timeManager: TimeManager,
    private val coroutineScope: CoroutineScope
) {
    val collision = MutableStateFlow(false)

    init {
        coroutineScope.launch {
            timeManager.deltaTime.collect {
                val player = playerLogic.player.value
                val block = blockLogic.blockPosition.value

                val topPipeCollided = collided(player, block.topPipe)
                val bottomPipeCollided = collided(player, block.bottomPipe)

                val collisionHappened = topPipeCollided || bottomPipeCollided

                collision.value = collisionHappened
            }
        }
    }

    private fun collided(
        player: Player,
        pipe: Pipe
    ) = player.rect.overlaps(pipe.rect)
}