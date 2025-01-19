package com.singularityindonesia.opendaimon.sys.daimon.lib

import kotlinx.coroutines.flow.MutableStateFlow

@JvmInline
value class Cortisol(val value: Float)

@JvmInline
value class Adrenalin(val value: Float)

@JvmInline
value class Dopamine(val value: Float)

@JvmInline
value class Endorphin(val value: Float)

@JvmInline
value class Serotonin(val value: Float)

class HormoneGland {
    val cortisol = MutableStateFlow(Cortisol(0f))
    val adrenalin = MutableStateFlow(Adrenalin(0f))
    val dopamine = MutableStateFlow(Dopamine(0f))
    val endorphin = MutableStateFlow(Endorphin(0f))
    val serotonin = MutableStateFlow(Serotonin(0f))
}