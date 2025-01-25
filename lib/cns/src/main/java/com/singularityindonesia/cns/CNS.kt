package com.singularityindonesia.cns

import com.singularityindonesia.cns.lobe.ControlLobe
import com.singularityindonesia.cns.lobe.MotorLobe
import com.singularityindonesia.cns.lobe.SensoryLobe
import com.singularityindonesia.cns.lobe.VisualLobe

/**
 * Central Nervous System Model
 */
class CNS {
    val visualLobe: VisualLobe = VisualLobe()
    val sensoryLobe: SensoryLobe = SensoryLobe()
    val motorLobe: MotorLobe = MotorLobe()
    val controlLobe: ControlLobe = ControlLobe()
    val commissuralFiber = TODO()
    val associationFiber = TODO()
    val projectionFiber = TODO()

    init {

    }
}