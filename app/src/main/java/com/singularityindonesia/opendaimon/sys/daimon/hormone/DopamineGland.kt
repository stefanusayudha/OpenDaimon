package com.singularityindonesia.opendaimon.sys.daimon.hormone

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

@JvmInline
value class Dopamine(val value: Float)

class DopamineGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Dopamine(0f))
}