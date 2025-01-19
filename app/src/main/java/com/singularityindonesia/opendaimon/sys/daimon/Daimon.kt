package com.singularityindonesia.opendaimon.sys.daimon

import com.singularityindonesia.opendaimon.sys.daimon.lib.HormoneGland
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow

class Daimon {
    val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    val hormoneGland = HormoneGland()
    val instruction: Flow<UShort> = TODO()
}
