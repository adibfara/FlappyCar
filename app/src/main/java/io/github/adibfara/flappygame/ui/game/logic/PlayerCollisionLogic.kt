package io.github.adibfara.flappygame.ui.game.logic

import kotlinx.coroutines.CoroutineScope

class PlayerCollisionLogic(
    private val playerLogic: PlayerLogic,
    private val blockLogic: BlockLogic,
    private val coroutineScope: CoroutineScope
) {

}