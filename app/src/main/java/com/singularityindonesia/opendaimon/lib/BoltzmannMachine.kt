package com.singularityindonesia.opendaimon.lib

import kotlin.math.exp
import kotlin.random.Random

class BoltzmannMachine(
    private val numVisible: Int,
    private val numHidden: Int
) {
    private var weights: Array<FloatArray> = Array(numVisible) { FloatArray(numHidden) }
    private var bias: FloatArray = FloatArray(numHidden)

    init {
        // Initialize weights and biases with random values
        initializeWeightsAndBiases()
    }

    private fun initializeWeightsAndBiases() {
        val random = Random.Default
        for (i in 0 until numVisible) {
            for (j in 0 until numHidden) {
                weights[i][j] = random.nextFloat() * 0.1f
            }
        }
        for (i in 0 until numHidden) {
            bias[i] = random.nextFloat() * 0.1f
        }
    }

    fun sigmoid(x: Float): Float {
        return 1.0f / (1.0f + exp(-x))
    }

    fun visibleToHidden(visible: FloatArray): FloatArray {
        val hidden = FloatArray(numHidden)
        for (i in 0 until numHidden) {
            var sum = bias[i]
            for (j in 0 until numVisible) {
                sum += visible[j] * weights[j][i]
            }
            hidden[i] = sigmoid(sum)
        }
        return hidden
    }

    fun hiddenToVisible(hidden: FloatArray): FloatArray {
        val visible = FloatArray(numVisible)
        for (i in 0 until numVisible) {
            var sum = 0.0f
            for (j in 0 until numHidden) {
                sum += hidden[j] * weights[i][j]
            }
            visible[i] = sigmoid(sum + bias[i])
        }
        return visible
    }

    fun train(data: Array<FloatArray>, learningRate: Float, epochs: Int) {
        for (epoch in 0 until epochs) {
            for (visible in data) {
                val hidden = visibleToHidden(visible)
                val visibleReconstructed = hiddenToVisible(hidden)
                val error = FloatArray(numVisible)
                for (i in 0 until numVisible) {
                    error[i] = visible[i] - visibleReconstructed[i]
                }
                for (i in 0 until numVisible) {
                    for (j in 0 until numHidden) {
                        weights[i][j] += learningRate * error[i] * hidden[j]
                    }
                }
                for (i in 0 until numHidden) {
                    bias[i] += learningRate * error[i]
                }
            }
        }
    }

    fun predict(visible: FloatArray): FloatArray {
        val hidden = visibleToHidden(visible)
        val visibleReconstructed = hiddenToVisible(hidden)
        return visibleReconstructed
    }
}