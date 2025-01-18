package com.singularityindonesia.opendaimon.sys.daimon.hormone

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

@JvmInline
value class Serotonin(val value: Float)

class SerotoninGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Serotonin(0f))
}