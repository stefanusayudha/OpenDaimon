package com.singularityindonesia.opendaimon.sys.daimon.lib

import android.content.Context
import android.hardware.usb.UsbManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import com.singularityindonesia.serial.SerialHost
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

