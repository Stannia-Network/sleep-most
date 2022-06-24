package me.mrgeneralq.sleepmost.models

import me.mrgeneralq.sleepmost.enums.DreamType
import me.mrgeneralq.sleepmost.models.abstracts.Configurable

data class Dream(
    var name: String,
    var dreamType: DreamType = DreamType.NORMAL,
    var chance: Double = 100.0,
    var duration: Int = -1,
    val spawnLocations: MutableMap<String, SimpleLocation> = mutableMapOf(),

    override val id: String = name
) : Configurable<String>() {
    constructor() : this("") {}
}