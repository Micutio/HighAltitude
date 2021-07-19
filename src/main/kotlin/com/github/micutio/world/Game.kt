package com.github.micutio.world

import com.github.micutio.attributes.types.Player
import com.github.micutio.extensions.GameEntity

class Game(
    val world: World,
    val player: GameEntity<Player>
) {

    companion object {

        fun create(
            player: GameEntity<Player>,
            world: World
        ) = Game(
            world = world,
            player = player
        )

    }

}