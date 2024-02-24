package io.github.adibfara.flappygame.ui.game.engine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class TimeManager {
    val deltaTime = flow {
        while (true) {
            val time = 1
            delay(time.toLong())
            emit(time.toFloat() * 2)
        }
    }

}