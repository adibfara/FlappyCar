package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.model.GameStatus
import io.github.adibfara.flappygame.ui.game.model.Viewport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BlockSpawnerLogic(
    private val blockMovementLogic: BlockMovementLogic,
    private val gameStatusLogic: GameStatusLogic,
    private val coroutineScope: CoroutineScope,
    private val viewport: Viewport
) {

    private val blockCreator = BlockCreator(viewport)

    init {
        coroutineScope.launch {
            gameStatusLogic.gameState.mapLatest { gameState ->
                if (gameState == GameStatus.Started) {
                    spawn()
                }
            }.collect {

            }
        }
    }

    private suspend fun spawn() {
        while (true) {
            delay(1500)
            val existingBlock = blockMovementLogic.blockPosition.value.firstOrNull {
                it.topPipe.x < -100
            }
            val createBlock = blockCreator.createBlock()
            if (existingBlock != null) {
                val updatedBlock = existingBlock.copy(
                    hasBeenScored = false,
                    topPipe = createBlock.topPipe,
                    bottomPipe = createBlock.bottomPipe
                )
                blockMovementLogic.updateBlock(existingBlock, updatedBlock)
            } else {
                blockMovementLogic.addBlock(createBlock)
            }
        }
    }
}