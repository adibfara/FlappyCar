package io.github.adibfara.flappygame.ui.game.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.adibfara.flappygame.R
import io.github.adibfara.flappygame.ui.game.model.Pipe


@Composable
internal fun Pipe(pipe: Pipe, modifier: Modifier = Modifier) {
    Image(
        painterResource(id = R.drawable.pipe),
        contentDescription = null,
        modifier
            .offset {
                IntOffset(pipe.x.dp.roundToPx(), pipe.topY.dp.roundToPx())
            }
            .size(pipe.width.dp, (pipe.bottomY - pipe.topY).dp)
            .background(Color.Green),
        contentScale = ContentScale.FillBounds
    )
}
