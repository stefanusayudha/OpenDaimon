package com.singularityindonesia.opendaimon.lib

import org.junit.Assert
import org.junit.Test
import kotlin.math.exp

class BoltzmannMachineTest {
    @Test
    fun testSigmoid() {
        val bm = BoltzmannMachine(5, 10)
        val x = 0.5f
        val expected = 1.0f / (1.0f + exp(-x))
        Assert.assertEquals(expected, bm.sigmoid(x), 0.001f)
    }

//    @Test
//    fun testVisibleToHidden() {
//        val bm = BoltzmannMachine(5, 10)
//        val visible = floatArrayOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f)
//        val hidden = bm.visibleToHidden(visible)
//        assertContentEquals(floatArrayOf(0.59868766f, 0.6224593f, 0.6462309f, 0.6700025f, 0.6937741f, 0.7175457f, 0.7413173f, 0.7650889f, 0.7888605f, 0.8126321f), hidden, 0.001f)
//    }
//
//    @Test
//    fun testHiddenToVisible() {
//        val bm = BoltzmannMachine(5, 10)
//        val hidden = floatArrayOf(0.59868766f, 0.6224593f, 0.6462309f, 0.6700025f, 0.6937741f, 0.7175457f, 0.7413173f, 0.7650889f, 0.7888605f, 0.8126321f)
//        val visible = bm.hiddenToVisible(hidden)
//        assertContentEquals(floatArrayOf(0.59868766f, 0.6224593f, 0.6462309f, 0.6700025f, 0.6937741f), visible, 0.001f)
//    }

    @Test
    fun testTrain() {
        val bm = BoltzmannMachine(5, 10)
        val data = arrayOf(
            floatArrayOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f),
            floatArrayOf(0.5f, 0.4f, 0.3f, 0.2f, 0.1f),
            floatArrayOf(0.2f, 0.3f, 0.1f, 0.5f, 0.4f),
            floatArrayOf(0.4f, 0.5f, 0.2f, 0.3f, 0.1f),
            floatArrayOf(0.3f, 0.1f, 0.5f, 0.4f, 0.2f)
        )
        val learningRate = 0.1f
        val epochs = 100
        bm.train(data, learningRate, epochs)
        val visible = floatArrayOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f)
        val predictedVisible = bm.predict(visible)
        Assert.assertTrue(predictedVisible.isNotEmpty())
    }

    @Test
    fun testPredict() {
        val bm = BoltzmannMachine(5, 10)
        val data = arrayOf(
            floatArrayOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f),
            floatArrayOf(0.5f, 0.4f, 0.3f, 0.2f, 0.1f),
            floatArrayOf(0.2f, 0.3f, 0.1f, 0.5f, 0.4f),
            floatArrayOf(0.4f, 0.5f, 0.2f, 0.3f, 0.1f),
            floatArrayOf(0.3f, 0.1f, 0.5f, 0.4f, 0.2f)
        )
        val learningRate = 0.1f
        val epochs = 100
        bm.train(data, learningRate, epochs)
        val visible = floatArrayOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f)
        val predictedVisible = bm.predict(visible)
        println("aksjdnakdn $predictedVisible")
//        assertContentEquals(floatArrayOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f), predictedVisible, 0.001f)
    }
}