package com.github.micutio.builders

import com.github.micutio.blocks.GameBlock
import com.github.micutio.blocks.GameBlockFactory
import com.github.micutio.extensions.sameLevelNeighborsShuffled
import com.github.micutio.world.World
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D

class WorldBuilder(private val worldSize: Size3D) {

    private val width = worldSize.xLength
    private val height = worldSize.yLength
    private var blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()

    fun makeCaves(): WorldBuilder {
        return randomizeTiles().smooth(8)
    }

    fun build(visibleSize: Size3D): World = World(blocks, visibleSize, worldSize)

    private fun randomizeTiles(): WorldBuilder {
        forAllPositions { pos ->
            blocks[pos] = if (Math.random() < 0.5) {
                GameBlockFactory.floor()
            } else GameBlockFactory.wall()
        }
        return this
    }

    private fun smooth(iterations: Int): WorldBuilder {
        val newBlocks = mutableMapOf<Position3D, GameBlock>()
        repeat(iterations) {
            forAllPositions { pos ->
                val (x, y, z) = pos
                var floors = 0
                var rocks = 0
                pos.sameLevelNeighborsShuffled().plus(pos).forEach { neighbor ->
                    blocks.whenPresent(neighbor) { block ->
                        if (block.isFloor) {
                            floors += 1
                        } else rocks += 1
                    }
                }
                newBlocks[Position3D.create(x, y, z)] =
                    if (floors >= rocks) GameBlockFactory.floor() else GameBlockFactory.wall()
            }
            blocks = newBlocks
        }
        return this
    }

    private fun forAllPositions(fn: (Position3D) -> Unit) {
        worldSize.fetchPositions().forEach(fn)
    }

    private fun MutableMap<Position3D, GameBlock>.whenPresent(pos: Position3D, fn: (GameBlock) -> Unit) {
        this[pos]?.let(fn)
    }

}