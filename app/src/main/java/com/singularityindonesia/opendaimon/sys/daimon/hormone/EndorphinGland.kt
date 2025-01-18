package com.singularityindonesia.opendaimon.sys.daimon.hormone

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

@JvmInline
value class Endorphin(val value: Float)

class EndorphinGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Endorphin(0f))
}