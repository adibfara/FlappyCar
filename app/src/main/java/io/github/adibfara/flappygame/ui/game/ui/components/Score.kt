package io.github.adibfara.flappygame.ui.game.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.adibfara.flappygame.ui.game.di.GameDI

@Composable
internal fun BoxScope.Score(di: GameDI) {
    Column(
        Modifier
            .align(Alignment.TopEnd)
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            "Score",
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            di.gameScoreLogic.score.collectAsState().value.toString(),
            color = Color.White,
            fontSize = 22.sp
        )
    }

}
