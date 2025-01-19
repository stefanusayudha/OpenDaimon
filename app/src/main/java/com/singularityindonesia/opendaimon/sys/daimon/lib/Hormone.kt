package com.singularityindonesia.opendaimon.sys.daimon.lib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

@JvmInline
value class Cortisol(val value: Float)

class CortisolGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Cortisol(0f))
}

@JvmInline
value class Adrenalin(val value: Float)

class AdrenalGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Adrenalin(0f))
}

@JvmInline
value class Dopamine(val value: Float)

class DopamineGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Dopamine(0f))
}

@JvmInline
value class Endorphin(val value: Float)

class EndorphinGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Endorphin(0f))
}

@JvmInline
value class Serotonin(val value: Float)

class SerotoninGland(
    private val scope: CoroutineScope
) {
    val hormoneLevel = MutableStateFlow(Serotonin(0f))
}