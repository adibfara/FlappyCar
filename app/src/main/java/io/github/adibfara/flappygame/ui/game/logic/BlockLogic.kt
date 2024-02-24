package io.github.adibfara.flappygame.ui.game.logic

import android.os.Parcel
import android.os.Parcelable
import io.github.adibfara.flappygame.ui.game.engine.GameLogic
import io.github.adibfara.flappygame.ui.game.model.Block
import io.github.adibfara.flappygame.ui.game.model.Pipe
import io.github.adibfara.flappygame.ui.game.model.Viewport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlockLogic(
    private val viewport: Viewport
) : GameLogic, OnGameOverLogic {
    private val blockCreator = BlockCreator(viewport)
    private val _blockPosition = MutableStateFlow(listOf(blockCreator.createBlock()))
    val blockPosition: StateFlow<List<Block>> = _blockPosition

    init {
        resetBlock()
    }

    override fun onUpdate(deltaTime: Float) {
        updateBlockX { x ->
            var newX = x - (deltaTime * 0.2f)
            if (newX < -100f) {
                newX = viewport.width
            }
            newX
        }
    }

    private fun updateBlockX(update: (Float) -> Float) {
        _blockPosition.update { blocks ->
            val block = blocks.first()
            val newBlock = block.copy(
                topPipe = block.topPipe.copy(
                    x = update(block.topPipe.x)
                ),
                bottomPipe = block.bottomPipe.copy(
                    x = update(block.bottomPipe.x)
                )
            )
            blocks.toMutableList().apply {
                set(0, newBlock)
            }
        }
    }

    fun scoreBlock(block: Block) {
        _blockPosition.update {
            it.map { it.copy(hasBeenScored = true) }
        }
    }

    override fun onGameOver() {
        resetBlock()
    }

    private fun resetBlock() {
        _blockPosition.update { listOf(blockCreator.createBlock()) }
        updateBlockX { _ -> viewport.width }
    }

}