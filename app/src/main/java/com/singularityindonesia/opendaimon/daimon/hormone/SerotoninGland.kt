package com.singularityindonesia.opendaimon.daimon.hormone

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

class SerotoninGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(0f)
}