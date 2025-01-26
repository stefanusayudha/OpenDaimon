package com.singularityindonesia.cns.neuron

import com.singularityindonesia.cns.lib.Position3D

typealias NeuronID = Long
typealias Weight = Double

data class Neuron(
    val position: Position3D,
    val dendritConnections: Array<Pair<Signal, NeuronID>>,
    val synapseConnection: Array<Pair<Signal, NeuronID>>
) {
    private suspend fun forward(signal: Signal): Result<Pair<NeuronID, Weight>> {
        return TODO("Forward signal to neuron")
    }

    suspend fun ping(signal: Signal): Result<Weight> {
        return TODO("Send signal to neuron and return calculated weight")
    }
}
