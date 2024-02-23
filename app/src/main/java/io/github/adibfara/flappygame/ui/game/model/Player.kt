package io.github.adibfara.flappygame.ui.game.model

data class Player(val y: Float, val speed: Float) {
    companion object {
        const val acceleration = 0.002f
    }
}