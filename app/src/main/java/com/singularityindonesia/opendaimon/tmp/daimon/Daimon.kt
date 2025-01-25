package com.singularityindonesia.opendaimon.tmp.daimon

import android.content.Context
import com.singularityindonesia.opendaimon.tmp.daimon.lib.HormoneGland
import com.singularityindonesia.sensor.Sensors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Daimon(
    val hormoneGland: HormoneGland,
    val sensors: Sensors,
) {
    constructor(context: Context) : this(hormoneGland = HormoneGland(), sensors = Sensors(context))

    // using system dispatcher, coroutine will live forever in the background unless it's being stopped
    private val coroutineScope: CoroutineScope =
        CoroutineScope(Dispatchers.Default + SupervisorJob())

    val instruction: Flow<UShort> = flowOf()

    fun start() {
        sensors.start()
    }

    fun stop() {
        sensors.stop()
        coroutineScope.cancel()
    }
}
