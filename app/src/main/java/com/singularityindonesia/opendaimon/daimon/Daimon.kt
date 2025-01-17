package com.singularityindonesia.opendaimon.daimon

import com.singularityindonesia.opendaimon.daimon.hormone.AdrenalGland
import com.singularityindonesia.opendaimon.daimon.hormone.CortisolGland
import com.singularityindonesia.opendaimon.daimon.hormone.DopamineGland
import com.singularityindonesia.opendaimon.daimon.hormone.EndorphinGland
import com.singularityindonesia.opendaimon.daimon.hormone.SerotoninGland
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class Daimon {
    val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    val adrenalGland = AdrenalGland(coroutineScope)
    val cortisolGland = CortisolGland(coroutineScope)
    val dopamineGland = DopamineGland(coroutineScope)
    val endorphinGland = EndorphinGland(coroutineScope)
    val serotoninGland = SerotoninGland(coroutineScope)
}