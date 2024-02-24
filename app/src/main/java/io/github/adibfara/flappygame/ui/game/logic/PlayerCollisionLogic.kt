package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.engine.GameLogic
import io.github.adibfara.flappygame.ui.game.model.Pipe
import io.github.adibfara.flappygame.ui.game.model.Player
import kotlinx.coroutines.flow.MutableStateFlow

class PlayerCollisionLogic(
    private val playerLogic: PlayerLogic,
    private val blockMovementLogic: BlockMovementLogic,
    private val gameStatusLogic: GameStatusLogic
) : GameLogic {
    val collision = MutableStateFlow(false)

    override fun onUpdate(deltaTime: Float) {
        val player = playerLogic.player.value
        val blocks = blockMovementLogic.blockPosition.value

        val block = blocks.firstOrNull() ?: return

        val topPipeCollided = collided(player, block.topPipe)
        val bottomPipeCollided = collided(player, block.bottomPipe)

        val collisionHappened = topPipeCollided || bottomPipeCollided

        if (collisionHappened) {
            gameStatusLogic.gameOver()
        }
        collision.value = collisionHappened
    }

    private fun collided(
        player: Player,
        pipe: Pipe
    ) = player.rect.overlaps(pipe.rect)
}