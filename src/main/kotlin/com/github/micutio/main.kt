package com.github.micutio

import com.github.micutio.view.StartView
import org.hexworks.zircon.api.*
import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.screen.Screen

fun main(args: Array<String>) {

    val grid = SwingApplications.startTileGrid(
        AppConfig.newBuilder()
            .withDefaultTileset(CP437TilesetResources.rogueYun16x16())
            .build()
    )
    StartView(grid).dock()
}
