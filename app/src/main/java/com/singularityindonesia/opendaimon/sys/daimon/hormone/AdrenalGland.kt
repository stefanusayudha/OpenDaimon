package com.singularityindonesia.opendaimon.sys.daimon.hormone

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

@JvmInline
value class Adrenalin(val value: Float)

class AdrenalGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Adrenalin(0f))
}

