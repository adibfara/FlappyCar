package io.github.adibfara.flappygame.ui.game.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import io.github.adibfara.flappygame.ui.game.di.GameDI
import io.github.adibfara.flappygame.ui.game.model.GameStatus

@Composable
internal fun BoxScope.GameOverUI(di: GameDI) {
    val notStarted =
        di.gameStatusLogic.gameState.collectAsState().value == GameStatus.GameOver
    if (!notStarted) return

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f))
    ) {

        Text(
            "Game over!",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 22.sp
        )
    }

}