package com.singularityindonesia.opendaimon.sys.daimon

import androidx.compose.runtime.snapshotFlow
import com.singularityindonesia.opendaimon.lib.BoltzmannMachine
import com.singularityindonesia.opendaimon.sys.daimon.hormone.AdrenalGland
import com.singularityindonesia.opendaimon.sys.daimon.hormone.CortisolGland
import com.singularityindonesia.opendaimon.sys.daimon.hormone.DopamineGland
import com.singularityindonesia.opendaimon.sys.daimon.hormone.EndorphinGland
import com.singularityindonesia.opendaimon.sys.daimon.hormone.SerotoninGland
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class Daimon {
    val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val boltzmannMachine = BoltzmannMachine(5,10)
    val hormoneGlands = HormoneGlands(coroutineScope)
}

class HormoneGlands(
    coroutineScope: CoroutineScope
) {
    val adrenalGland = AdrenalGland(coroutineScope)
    val cortisolGland = CortisolGland(coroutineScope)
    val dopamineGland = DopamineGland(coroutineScope)
    val endorphinGland = EndorphinGland(coroutineScope)
    val serotoninGland = SerotoninGland(coroutineScope)
}