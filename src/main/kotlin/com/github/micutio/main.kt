package com.github.micutio

import com.github.micutio.core.GameConfig
import com.github.micutio.view.StartView
import org.hexworks.zircon.api.SwingApplications

fun main(args: Array<String>) {

    val grid = SwingApplications.startTileGrid(GameConfig.buildAppConfig())
    StartView(grid).dock()
}
