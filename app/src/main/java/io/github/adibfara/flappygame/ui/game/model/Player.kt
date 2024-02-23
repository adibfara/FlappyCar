package io.github.adibfara.flappygame.ui.game.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp

data class Player(
    val y: Float,
    val speed: Float,
    val size: Size = Size(40f, 20f)
) {
    val rect = Rect(Offset(0f, y), size.toComposeSize())

    companion object {
        const val acceleration = 0.001f
    }
}