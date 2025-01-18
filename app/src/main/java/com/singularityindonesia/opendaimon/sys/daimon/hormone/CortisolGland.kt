package com.singularityindonesia.opendaimon.sys.daimon.hormone

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

@JvmInline
value class Cortisol(val value: Float)

class CortisolGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Cortisol(0f))
}