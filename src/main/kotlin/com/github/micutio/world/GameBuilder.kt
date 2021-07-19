package com.github.micutio.world

import com.github.micutio.attributes.types.Player
import com.github.micutio.builders.EntityFactory
import com.github.micutio.builders.WorldBuilder
import com.github.micutio.core.GameConfig
import com.github.micutio.core.GameConfig.LOG_AREA_HEIGHT
import com.github.micutio.core.GameConfig.SIDEBAR_WIDTH
import com.github.micutio.core.GameConfig.WINDOW_HEIGHT
import com.github.micutio.core.GameConfig.WINDOW_WIDTH
import com.github.micutio.core.GameConfig.WORLD_SIZE
import com.github.micutio.extensions.GameEntity
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D

class GameBuilder(val worldSize: Size3D) {

    private val visibleSize = Size3D.create(
        xLength = WINDOW_WIDTH - SIDEBAR_WIDTH,
        yLength = WINDOW_HEIGHT - LOG_AREA_HEIGHT,
        zLength = 1
    )

    val world = WorldBuilder(worldSize)
        .makeCaves()
        .build(visibleSize = visibleSize)

    fun buildGame(): Game {
        prepareWorld()

        val player = addPlayer()

        return Game.create(
            player = player,
            world = world
        )
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize.zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        val player = EntityFactory.newPlayer()
        world.addAtEmptyPosition(
            player,
            offset = Position3D.create(0, 0, GameConfig.DUNGEON_LEVELS - 1),
            size = world.visibleSize.copy(zLength = 0)
        )
        return player
    }

    companion object {
        fun create() = GameBuilder(
            worldSize = WORLD_SIZE
        ).buildGame()
    }


}