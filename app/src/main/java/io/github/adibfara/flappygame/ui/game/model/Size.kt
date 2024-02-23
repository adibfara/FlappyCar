package io.github.adibfara.flappygame.ui.game.model

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class Size(val width: Float, val height: Float)

fun Size.toDpSize(): DpSize {
    return DpSize(width.dp, height.dp)
}
