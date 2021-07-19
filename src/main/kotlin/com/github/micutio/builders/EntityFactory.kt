package com.github.micutio.builders

import com.github.micutio.attributes.EntityPosition
import com.github.micutio.attributes.EntityTile
import com.github.micutio.attributes.types.Player
import com.github.micutio.core.GameTileRepository.PLAYER
import com.github.micutio.world.GameContext
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType

fun <T: EntityType> newGameEntityOfType(
    type: T,
    init: EntityBuilder<T, GameContext>.() -> Unit
) = newEntityOfType(type, init)

object EntityFactory {

    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(EntityPosition(), EntityTile(PLAYER))
        behaviors()
        facets()
    }

}