package io.github.adibfara.flappygame.ui.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.adibfara.flappygame.ui.game.engine.gameCoroutineScope
import io.github.adibfara.flappygame.ui.game.logic.BlockLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.logic.TimeManager
import io.github.adibfara.flappygame.ui.game.model.Pipe
import io.github.adibfara.flappygame.ui.game.ui.components.Block
import io.github.adibfara.flappygame.ui.game.ui.components.Player

@Composable
fun Game(modifier: Modifier = Modifier) {
    val timeManager = remember {
        TimeManager()
    }
    val coroutineScope = remember {
        gameCoroutineScope()
    }
    val playerLogic = remember {
        PlayerLogic(timeManager, coroutineScope)
    }

    val blockLogic = remember {
        BlockLogic(timeManager, coroutineScope)
    }
    val playerCollisionLogic = remember {
        PlayerCollisionLogic(playerLogic, blockLogic, timeManager, coroutineScope)
    }

    Box(modifier.clickable {
        playerLogic.jump()
    }) {
        Player(Modifier, playerLogic, playerCollisionLogic)
        Block(blockLogic)
    }
}



