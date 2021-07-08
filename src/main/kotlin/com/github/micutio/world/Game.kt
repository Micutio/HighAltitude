package com.github.micutio.world

import com.github.micutio.builders.WorldBuilder
import com.github.micutio.core.GameConfig
import org.hexworks.zircon.api.data.Size3D

class Game(val world: World) {

    companion object {

        fun create(
            worldSize: Size3D = GameConfig.WORLD_SIZE,
            visibleSize: Size3D = GameConfig.GAME_AREA_SIZE
        ) = Game(
            WorldBuilder(worldSize)
                .makeCaves()
                .build(visibleSize)
        )

    }

}