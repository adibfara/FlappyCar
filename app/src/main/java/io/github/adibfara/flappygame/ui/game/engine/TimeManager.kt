package io.github.adibfara.flappygame.ui.game.engine

import androidx.compose.runtime.withFrameMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class TimeManager(coroutineScope: CoroutineScope) {
    val deltaTime by lazy {
        flow {
            while (true) {
                var lastFrame: Long? = null
                while (true) {
                    var frame = 0.0f
                    withFrameMillis {
                        if (lastFrame != null) {
                            frame = (it - lastFrame!!).toFloat()
                        }
                        lastFrame = it
                    }

                    if (frame != 0.0f) {
                        emit(frame)
                    }
                }
            }
        }.shareIn(coroutineScope, SharingStarted.Eagerly)
    }

}