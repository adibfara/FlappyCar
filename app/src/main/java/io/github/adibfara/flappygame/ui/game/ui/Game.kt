package io.github.adibfara.flappygame.ui.game.ui

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Shader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import io.github.adibfara.flappygame.R
import io.github.adibfara.flappygame.ui.game.engine.LogicManager
import io.github.adibfara.flappygame.ui.game.engine.gameCoroutineScope
import io.github.adibfara.flappygame.ui.game.logic.BlockLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerCollisionLogic
import io.github.adibfara.flappygame.ui.game.logic.PlayerLogic
import io.github.adibfara.flappygame.ui.game.engine.TimeManager
import io.github.adibfara.flappygame.ui.game.engine.compose.toPx
import io.github.adibfara.flappygame.ui.game.logic.GameOverManager
import io.github.adibfara.flappygame.ui.game.logic.GameScoreLogic
import io.github.adibfara.flappygame.ui.game.logic.GameStatusLogic
import io.github.adibfara.flappygame.ui.game.logic.OnGameOverLogic
import io.github.adibfara.flappygame.ui.game.model.Viewport
import io.github.adibfara.flappygame.ui.game.ui.components.Block
import io.github.adibfara.flappygame.ui.game.ui.components.Player
import kotlinx.coroutines.delay

@Composable
fun Game(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {

        val maxWidthPx = maxWidth.toPx()
        val maxHeightPx = maxHeight.toPx()

        val viewPort = remember {
            Viewport(maxWidthPx, maxHeightPx)
        }

        val timeManager = remember {
            TimeManager()
        }

        val gameStatusLogic = remember {
            GameStatusLogic()
        }

        val coroutineScope = remember {
            gameCoroutineScope()
        }
        val playerLogic = remember {
            PlayerLogic(gameStatusLogic)
        }

        val blockLogic = remember {
            BlockLogic(viewPort)
        }
        val playerCollisionLogic = remember {
            PlayerCollisionLogic(playerLogic, blockLogic, gameStatusLogic)
        }

        val gameScoreLogic = remember {
            GameScoreLogic(playerLogic, blockLogic)
        }

        val gameOverManager = remember {
            val onGameOverLogics: List<OnGameOverLogic> =
                listOf(playerLogic, blockLogic, gameScoreLogic)
            GameOverManager(gameStatusLogic, onGameOverLogics, coroutineScope)
        }
        val logicManager = remember {
            val logics = listOf(
                playerLogic,
                blockLogic,
                playerCollisionLogic,
                gameScoreLogic,
                gameStatusLogic,
            )
            LogicManager(logics, gameStatusLogic, timeManager, coroutineScope)
        }
        Box(
            Modifier
                .fillMaxSize()
                .clickable {
                    playerLogic.jump()
                }) {
            Box {
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
                            .resizeTo(maxHeightPx.toInt()),
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
            Player(Modifier, playerLogic, playerCollisionLogic)
            Block(blockLogic)
            Text(
                gameScoreLogic.score.collectAsState().value.toString(),
                Modifier.align(Alignment.TopEnd)
            )
            Text(gameStatusLogic.gameState.collectAsState().value.toString())
        }
    }
}


fun Bitmap.resizeTo(maxHeight: Int): Bitmap {
    val sourceWidth: Int = width
    val sourceHeight: Int = height
    val sourceRatio = sourceWidth.toFloat() / sourceHeight.toFloat()
    val targetWidth = (maxHeight.toFloat() * sourceRatio).toInt()
    return Bitmap.createScaledBitmap(this, targetWidth, maxHeight, true)
}

