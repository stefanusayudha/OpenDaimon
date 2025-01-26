package com.singularityindonesia.cns.lobe

import com.singularityindonesia.cns.neuron.Neuron

/**
 * Lobe of brain. Lobe is an area, a collection of neurons with specific context function
 */
sealed interface Lobe {
    val neurons: List<Neuron>
}