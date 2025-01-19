package com.singularityindonesia.opendaimon.sys.daimon.lib

import android.content.Context
import android.hardware.usb.UsbManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import com.singularityindonesia.opendaimon.sys.lib.io.SerialHost
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class SerialBus(
    private val serialHost: SerialHost
) {
    constructor(usbManager: UsbManager) : this(SerialHost(usbManager))

    constructor(context: Context) : this(context.getSystemService(Context.USB_SERVICE) as UsbManager)

    fun getDevices() = serialHost.getDevices()

    @Composable
    fun Display() {
        val availableDevices = produceState<String?>(null) {
            while (true) {
                val devices = getDevices()
                value = devices.map { it.device.deviceId }.joinToString(",").takeIf { it.isNotBlank() }
                delay(1.seconds)
            }
        }
        Text(availableDevices.value ?: "null")
    }
}