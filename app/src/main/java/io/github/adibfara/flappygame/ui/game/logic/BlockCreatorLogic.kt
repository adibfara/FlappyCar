package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.model.Viewport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BlockCreatorLogic(
    private val blockMovementLogic: BlockMovementLogic,
    private val coroutineScope: CoroutineScope,
    private val viewport: Viewport
) {

    private val blockCreator = BlockCreator(viewport)

    init {
        coroutineScope.launch {
            while (true) {
                delay(500)
                blockMovementLogic.addBlock(blockCreator.createBlock())
            }
        }
    }
}