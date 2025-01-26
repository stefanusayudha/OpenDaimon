package com.singularityindonesia.cns.lib

import com.singularityindonesia.cns.lobe.Lobe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class ModelStorage(
    private val coroutineScope: CoroutineScope,
    private val file: File
) {
    suspend fun load(): List<Lobe> = withContext(Dispatchers.IO) {
        emptyList() // fixme: load from storage
    }
}