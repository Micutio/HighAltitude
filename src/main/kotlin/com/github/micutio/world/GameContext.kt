package com.github.micutio.world

import com.github.micutio.attributes.types.Player
import com.github.micutio.extensions.GameEntity
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.w3c.dom.events.UIEvent

data class GameContext(
    val world: World,
    val screen: Screen,
    val uiEvent: UIEvent, val player: GameEntity<Player>
) : Context