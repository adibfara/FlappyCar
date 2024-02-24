package io.github.adibfara.flappygame.ui.game.engine.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp


@Composable
fun Dp.toPx(): Float {
    return with(LocalDensity.current) {
        toPx()
    }
}
