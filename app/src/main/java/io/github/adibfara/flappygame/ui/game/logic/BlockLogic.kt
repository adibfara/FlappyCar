package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.model.Block
import io.github.adibfara.flappygame.ui.game.model.Pipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlockLogic {
    private val _blockPosition = MutableStateFlow(Block(Pipe(0f, 200f, 0f), Pipe(300f, 450f, 0f)))
    val blockPosition: StateFlow<Block> = _blockPosition

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val deltaTime = flow {
        while (true) {
            val time = 20
            delay(time.toLong())
            emit(time.toFloat())
        }
    }

    init {
        updateBlockX { _ -> 400f }
        coroutineScope.launch {
            deltaTime.collect {
                updateBlockX { x -> x - 1 }
            }
        }
    }

    private fun updateBlockX(update: (Float) -> Float) {
        _blockPosition.update { block ->
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