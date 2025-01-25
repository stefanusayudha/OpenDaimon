package com.singularityindonesia.serial

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalTime
import java.time.LocalTime.now
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern

class SerialMonitor(
    private val host: SerialHost
) {
    fun subscribe(
        coroutineScope: CoroutineScope,
        onMessageReceived: (String) -> Unit
    ) {
        // todo: connect to all available devices
        val connection = host.getDevices().firstOrNull()?.let(host::connect)

        // todo: make byte buffer adjustable
        val buffer = ByteArray(1024)

        // todo: make timeout adjustable
        val timeout = 1000

        coroutineScope.launch(Dispatchers.IO) {
            while (isActive && connection != null) {
                try {
                    val numBytesRead = connection.read(buffer, timeout)
                    if (numBytesRead > 0) {
                        val message = String(buffer, 0, numBytesRead)
                        ensureActive()
                        onMessageReceived(message)
                    }
                } catch (e: IOException) {
                    Log.e("SerialHost", "Error reading serial message: ${e.message}")
                    break
                }
            }
        }
    }

    private val simpleMonitorMessageBuffer = MutableStateFlow<List<String>>(emptyList())

    @Composable
    fun SimpleMonitor(
        modifier: Modifier = Modifier,
        contentPadding: PaddingValues = PaddingValues(
            horizontal = 16.dp
        )
    ) {
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            subscribe(scope) { message ->
                simpleMonitorMessageBuffer.update { list ->
                    list.plus(
                        "${
                            now().format(ofPattern("HH:mm:ss"))
                        } $message"
                    ).takeLast(100)
                }
            }
        }

        val messages = simpleMonitorMessageBuffer.collectAsStateWithLifecycle()

        LazyColumn(
            modifier = modifier,
            contentPadding = contentPadding
        ) {
            items(messages.value.size) {
                Text(text = messages.value[it].replace("\n", ""))
            }
        }
    }
}