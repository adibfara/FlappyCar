package io.github.adibfara.flappygame.ui.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic

@Composable
fun Game(modifier: Modifier = Modifier) {
    val playerLogic = remember {
        PlayerLogic()
    }
    Box(modifier.clickable {
        playerLogic.jump()
    }) {

        val playerPosition = playerLogic.playerPosition.collectAsState()

        Box(
            Modifier
                .offset {
                    IntOffset(x = 0, y = playerPosition.value.y.toInt())
                }
                .size(50.dp)
                .background(Color.Black)
        ) {

        }
    }
}