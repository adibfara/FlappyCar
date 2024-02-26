package io.github.adibfara.flappygame.ui.game.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.adibfara.flappygame.ui.game.di.GameDI
import io.github.adibfara.flappygame.ui.game.model.GameStatus

@Composable
internal fun BoxScope.GameStartUI(di: GameDI) {
    val notStarted =
        di.gameStatusLogic.gameState.collectAsState().value == GameStatus.NotStarted
    if (notStarted)
        Text(
            "Tap To Start!",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 58.sp,
            fontWeight = FontWeight.Black
        )
}