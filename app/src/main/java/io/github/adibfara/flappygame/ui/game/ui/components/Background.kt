package io.github.adibfara.flappygame.ui.game.ui.components

import android.graphics.BitmapShader
import android.graphics.Shader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import io.github.adibfara.flappygame.R
import io.github.adibfara.flappygame.ui.game.engine.compose.toPx
import io.github.adibfara.flappygame.ui.game.ui.resizeTo
import kotlinx.coroutines.delay

@Composable
internal fun Background() {
    BoxWithConstraints {
        var scrollX by remember { mutableStateOf(0f) }
        LaunchedEffect(key1 = Unit) {
            while (true) {
                delay(1)
                scrollX -= 1f
            }
        }
        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.background).asAndroidBitmap()
                    .resizeTo(maxHeight.toPx().toInt()),
                Shader.TileMode.REPEAT,
                Shader.TileMode.MIRROR
            )
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.translate(scrollX, 0f)
                it.nativeCanvas.drawPaint(
                    paint
                )
                it.translate(0f, 0f)
            }
        }
    }
}
