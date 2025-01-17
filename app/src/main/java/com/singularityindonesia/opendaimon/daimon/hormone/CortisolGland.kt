package com.singularityindonesia.opendaimon.daimon.hormone

import kotlinx.coroutines.flow.MutableStateFlow

class CortisolGland {
    val hormoneLevel = MutableStateFlow(0f)
}