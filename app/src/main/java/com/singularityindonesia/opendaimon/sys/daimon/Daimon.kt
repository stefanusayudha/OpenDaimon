package com.singularityindonesia.opendaimon.sys.daimon

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class Daimon {
    val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val input = mutableListOf<Float>()
    val understanding = mutableListOf<Float>()
    val knowledge = mutableListOf<Float>()
    val wisdom = mutableListOf<Float>()
    val kindness = mutableListOf<Float>()
    val discipline = mutableListOf<Float>()
    val compassion = mutableListOf<Float>()
    val ambition = mutableListOf<Float>()
    val humility = mutableListOf<Float>()
    val bonding = mutableListOf<Float>()
    val dignity = mutableListOf<Float>()

    val instruction: Flow<UShort> = TODO()
}
