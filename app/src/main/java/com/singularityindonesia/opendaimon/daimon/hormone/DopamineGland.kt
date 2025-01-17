package com.singularityindonesia.opendaimon.daimon.hormone

import kotlinx.coroutines.flow.MutableStateFlow

class DopamineGland {
    val hormoneLevel = MutableStateFlow(0f)
}