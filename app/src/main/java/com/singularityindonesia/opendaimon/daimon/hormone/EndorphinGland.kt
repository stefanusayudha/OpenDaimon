package com.singularityindonesia.opendaimon.daimon.hormone

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

class EndorphinGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(0f)
}