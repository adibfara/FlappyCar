package io.github.adibfara.flappygame.ui.game.logic

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.adibfara.flappygame.ui.game.engine.GameLogic
import io.github.adibfara.flappygame.ui.game.model.Block
import io.github.adibfara.flappygame.ui.game.model.Pipe
import io.github.adibfara.flappygame.ui.game.model.Viewport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlockMovementLogic(
    private val viewport: Viewport
) : GameLogic, OnGameOverLogic {
    private val _blockPosition = MutableStateFlow<List<Block>>(listOf())
    val blockPosition: StateFlow<List<Block>> = _blockPosition

    init {
        resetBlock()
    }

    override fun onUpdate(deltaTime: Float) {
        updateBlockX { x ->
            x - (scrollAmount * deltaTime).dp
        }
    }

    private fun updateBlockX(update: (Dp) -> Dp) {
        _blockPosition.update { blocks ->
            blocks.map { block ->
                block.copy(
                    topPipe = block.topPipe.copy(
                        x = update(block.topPipe.x)
                    ),
                    bottomPipe = block.bottomPipe.copy(
                        x = update(block.bottomPipe.x)
                    )
                )
            }
        }
    }

    fun scoreBlock(block: Block) {
        _blockPosition.update {
            it.map {
                if (it.id != block.id) return@map it
                it.copy(hasBeenScored = true)
            }
        }
    }

    override fun onGameOver() {
        resetBlock()
    }

    private fun resetBlock() {
        _blockPosition.update { listOf() }
        updateBlockX { _ -> viewport.width }
    }

    fun addBlock(createBlock: Block) {
        _blockPosition.update {
            it + createBlock
        }
    }

    fun updateBlock(existingBlock: Block, updatedBlock: Block) {
        _blockPosition.update { blocks ->
            blocks.map { block ->
                if (block.id == existingBlock.id) {
                    return@map updatedBlock
                }
                block
            }
        }
    }

    companion object {
        const val scrollAmount = 0.2f
    }
}