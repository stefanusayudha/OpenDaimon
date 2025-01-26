package com.singularityindonesia.cns.neuron

import com.singularityindonesia.cns.lib.Position3D
typealias NeuronID = Double

data class Neuron(
    val position: Position3D,
    val dendritConnections: Array<Pair<Signal, NeuronID>>,
    val synapseConnection: Array<Pair<Signal,NeuronID>>
)
