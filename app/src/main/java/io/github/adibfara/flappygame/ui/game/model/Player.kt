package io.github.adibfara.flappygame.ui.game.model

import androidx.compose.ui.unit.dp

data class Player(val y: Float, val speed: Float, val size: Size = Size(90f, 30f)) {
    companion object {
        const val acceleration = 0.002f
    }
}