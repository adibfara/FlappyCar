package io.github.adibfara.flappygame.ui.game.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

data class Block(
    val topPipe: Pipe,
    val bottomPipe: Pipe,
    val hasBeenScored: Boolean = false
) {
    val scoreRect = Rect(topPipe.rect.bottomLeft, bottomPipe.rect.topRight)
}

data class Pipe(
    val topY: Float,
    val bottomY: Float,
    val x: Float,
    val width: Float = 20f
) {
    val rect = Rect(topLeft = Offset(x, topY), bottomRight = Offset(x + width, bottomY))
}