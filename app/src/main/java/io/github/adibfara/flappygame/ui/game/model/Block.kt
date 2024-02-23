package io.github.adibfara.flappygame.ui.game.model

data class Block(val topPipe: Pipe, val bottomPipe: Pipe)

data class Pipe(
    val topY: Float,
    val bottomY: Float,
    val x: Float
)