package com.singularityindonesia.cns

import com.singularityindonesia.cns.lib.ModelStorage
import com.singularityindonesia.cns.lobe.ControlLobe
import com.singularityindonesia.cns.lobe.DaimonLobe
import com.singularityindonesia.cns.lobe.MotorLobe
import com.singularityindonesia.cns.lobe.SensoryLobe
import com.singularityindonesia.cns.lobe.VisualLobe
import com.singularityindonesia.cns.neuron.Neuron
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Central Nervous System Model
 */
class CNS(
    private val coroutineScope: CoroutineScope,
    private val modelStorage: ModelStorage
) {
    /**
     * Neurons spreads in 3 dimensional brain hemisphere
     */
    var neurons: Array<Array<Neuron>> = emptyArray()

    private lateinit var visualLobe: VisualLobe
    private lateinit var sensoryLobe: SensoryLobe
    private lateinit var motorLobe: MotorLobe
    private lateinit var controlLobe: ControlLobe
    private lateinit var daimonLobe: DaimonLobe

    init {
        coroutineScope.launch {
            loadModel()
        }
    }

    suspend fun loadModel() = withContext(Dispatchers.IO){
        val model = modelStorage.load()

        model.forEach {
            when(it) {
                is ControlLobe -> {
                    controlLobe = it
                }
                is DaimonLobe -> {
                    daimonLobe = it
                }
                is MotorLobe -> {
                    motorLobe = it
                }
                is SensoryLobe -> {
                    sensoryLobe = it
                }
                is VisualLobe -> {
                    visualLobe = it
                }
            }
        }
    }

    fun audioInput() {
        TODO() // should go to visual processor
    }

    fun visualInput(byteArray: ByteArray) {
        TODO() // should go to visual processor
    }

    fun sensoryInput(byteArray: ByteArray) {
        TODO() // should go to sensory processor
    }

    fun motoricFeedBack(byteArray: ByteArray) {
        TODO() // should go to general input
    }
}
