package com.github.micutio.blocks

import com.github.micutio.core.GameTileRepository
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

class GameBlock(content: Tile = GameTileRepository.FLOOR) : BaseBlock<Tile>(
    emptyTile = GameTileRepository.EMPTY,
    tiles = persistentMapOf(BlockTileType.CONTENT to content)
        ) {

    val isFloor: Boolean
        get() = content == GameTileRepository.FLOOR

    val isWall: Boolean
        get() = content == GameTileRepository.WALL
}

object GameBlockFactory {

    fun floor() = GameBlock(GameTileRepository.FLOOR)

    fun wall() = GameBlock(GameTileRepository.WALL)

}