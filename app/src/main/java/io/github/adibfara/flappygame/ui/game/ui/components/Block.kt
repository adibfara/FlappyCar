package io.github.adibfara.flappygame.ui.game.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import io.github.adibfara.flappygame.ui.game.logic.BlockLogic

@Composable
internal fun Block(blockLogic: BlockLogic, modifier: Modifier = Modifier) {
    Box(modifier) {
        val blockPosition = blockLogic.blockPosition.collectAsState().value
        Pipe(blockPosition.topPipe)
        Pipe(blockPosition.bottomPipe)

    }
}