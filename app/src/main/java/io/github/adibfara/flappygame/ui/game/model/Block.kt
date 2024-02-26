package io.github.adibfara.flappygame.ui.game.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.UUID

data class Block(
    val topPipe: Pipe,
    val bottomPipe: Pipe,
    val hasBeenScored: Boolean = false
) {
    val id: String = UUID.randomUUID().toString()
    val scoreRect = Rect(topPipe.rect.bottomLeft, bottomPipe.rect.topRight)
}

data class Pipe(
    val topY: Dp,
    val bottomY: Dp,
    val x: Dp,
    val width: Dp = 20.dp
) {
    val rect = Rect(
        topLeft = Offset(x.value, topY.value),
        bottomRight = Offset((x + width).value, bottomY.value)
    )
}