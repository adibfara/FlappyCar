package io.github.adibfara.flappygame.ui.game.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.model.toDpSize

@Composable
internal fun Player(
    modifier: Modifier,
    playerLogic: PlayerLogic,
    playerCollisionLogic: PlayerCollisionLogic,
) {
    Box(modifier) {

        val player = playerLogic.player.collectAsState()
        val collided = playerCollisionLogic.collision.collectAsState().value
        Box(
            Modifier
                .offset {
                    IntOffset(x = 0, y = player.value.y.dp.roundToPx())
                }
                .size(player.value.size.toDpSize())
                .background(if (collided) Color.Red else Color.Black)
        ) {}
    }
}