package io.github.adibfara.flappygame.ui.game.logic

import io.github.adibfara.flappygame.ui.game.model.Block
import io.github.adibfara.flappygame.ui.game.model.Pipe
import io.github.adibfara.flappygame.ui.game.model.Viewport
import java.util.Random

class BlockCreator(private val viewport: Viewport) {
    private val minimumHeightPercentage = 0.1f
    private val minimumGateHeightPercentage = 0.3f
    private val maximumGateHeightPercentage = 0.5f
    fun createBlock(): Block {
        val totalHeight = viewport.height
        val maximumGateHeight = totalHeight * maximumGateHeightPercentage
        val usableHeight =
            (totalHeight * (1f - minimumHeightPercentage)).coerceAtMost(maximumGateHeight)
        val minimumHeight = usableHeight * minimumGateHeightPercentage
        val pipeMinimumHeight = (minimumHeightPercentage / 2f) * viewport.height

        val randomFloat = Random().nextFloat()
        val gateHeight = minimumHeight + (usableHeight - minimumHeight) * randomFloat
        val topPipeHeight = pipeMinimumHeight
        return Block(
            Pipe(0f, topPipeHeight, 0f),
            Pipe(topPipeHeight + gateHeight, totalHeight, 0f)
        )
    }
}