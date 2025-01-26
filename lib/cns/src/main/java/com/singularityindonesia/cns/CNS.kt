package com.singularityindonesia.cns

import com.singularityindonesia.cns.lobe.ControlLobe
import com.singularityindonesia.cns.lobe.DaimonLobe
import com.singularityindonesia.cns.lobe.MotorLobe
import com.singularityindonesia.cns.lobe.SensoryLobe
import com.singularityindonesia.cns.lobe.VisualLobe
import com.singularityindonesia.cns.neuron.Neuron

/**
 * Central Nervous System Model
 */
class CNS {
    /**
     * Neurons spreads in 3 dimensional brain hemisphere
     */
    var neurons: Array<Array<Neuron>> = emptyArray()

    val visualLobe: VisualLobe = VisualLobe()
    val sensoryLobe: SensoryLobe = SensoryLobe()
    val motorLobe: MotorLobe = MotorLobe()
    val controlLobe: ControlLobe = ControlLobe()
    val daimonLobe: DaimonLobe = DaimonLobe()

    init {

    }

    // todo: load model from disk
    fun load() {
        return TODO()
    }
}