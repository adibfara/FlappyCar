package io.github.adibfara.flappygame.ui.game.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.github.adibfara.flappygame.ui.game.engine.LogicManager
import io.github.adibfara.flappygame.ui.game.engine.gameCoroutineScope
import io.github.adibfara.flappygame.ui.game.logic.BlockLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.engine.TimeManager
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
        PlayerLogic()
    }

    val blockLogic = remember {
        BlockLogic()
    }
    val playerCollisionLogic = remember {
        PlayerCollisionLogic(playerLogic, blockLogic)
    }

    val logicManager = remember {
        val logics = listOf(playerLogic, blockLogic, playerCollisionLogic)
        LogicManager(logics, timeManager, coroutineScope)
    }
    Box(modifier.clickable {
        playerLogic.jump()
    }) {
        Player(Modifier, playerLogic, playerCollisionLogic)
        Block(blockLogic)
    }
}



