package io.github.adibfara.flappygame.ui.game.engine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun gameCoroutineScope() = CoroutineScope(Dispatchers.IO)