package com.singularityindonesia.opendaimon.sys.daimon

import android.content.Context
import com.singularityindonesia.opendaimon.sys.daimon.lib.HormoneGland
import com.singularityindonesia.opendaimon.sys.daimon.lib.Sensors
import com.singularityindonesia.opendaimon.sys.daimon.lib.SerialBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Daimon(
    context: Context,
    val hormoneGland: HormoneGland = HormoneGland(),
    val sensors: Sensors = Sensors(context),
    val serialBus: SerialBus = SerialBus(context),
) {
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
