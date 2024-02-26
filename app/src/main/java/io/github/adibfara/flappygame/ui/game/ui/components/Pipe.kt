package io.github.adibfara.flappygame.ui.game.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    Box(modifier = modifier) {
        Image(
            painterResource(id = R.drawable.pipe),
            contentDescription = null,
            Modifier
                .offset {
                    IntOffset(pipe.x.roundToPx(), pipe.topY.roundToPx())
                }
                .size(pipe.width, (pipe.bottomY - pipe.topY)),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painterResource(id = R.drawable.block_top),
            contentDescription = null,
            Modifier
                .width(pipe.width)
                .offset {
                    IntOffset(pipe.x.roundToPx(), pipe.topY.roundToPx())
                },
            contentScale = ContentScale.FillWidth
        )

        Image(
            painterResource(id = R.drawable.block_bottom),
            contentDescription = null,
            Modifier
                .width(pipe.width)
                .offset {
                    IntOffset(pipe.x.roundToPx(), pipe.bottomY.roundToPx() - 6.dp.roundToPx())
                },
            contentScale = ContentScale.FillWidth
        )
    }

}
