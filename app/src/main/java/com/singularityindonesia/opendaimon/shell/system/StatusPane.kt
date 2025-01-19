package com.singularityindonesia.opendaimon.shell.system

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularityindonesia.opendaimon.sys.daimon.lib.LocalMicrophone
import com.singularityindonesia.opendaimon.sys.daimon.lib.Microphone
import com.singularityindonesia.opendaimon.sys.daimon.lib.LocalSensors
import com.singularityindonesia.opendaimon.sys.daimon.lib.LocalSerialBus

// todo: should observe the daimon directly
@Composable
fun StatusPane(
    modifier: Modifier = Modifier
) {
    val sensors = LocalSensors.current
    val serialBus = LocalSerialBus.current
    val mic = LocalMicrophone.current

    val items by remember(sensors) {
        derivedStateOf {
            with(sensors) {
                listOf(
                    accelerometer to "Accelerometer",
                    gravity to "Gravity",
                    gyroscope to "Gyroscope",
                    linearAcceleration to "Linear Accel",
                    rotation to "Rotation",
                    geoRotation to "Geo Rotation",
                    proximity to "Proximity",
                    light to "Light",
                    ambienceTemp to "Amb Temp",
                    pressure to "Pressure",
                    humidity to "Humidity",
                )
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item {
            ListItem(
                headlineContent = {
                    Text("Microphone")
                },
                trailingContent = {
                    mic.Display()
                }
            )
        }
        item {
            ListItem(
                headlineContent = {
                    Text("Usb Device")
                },
                trailingContent = {
                    serialBus.Display()
                }
            )
        }
        items(items.size) { i ->
            val item = items[i]
            val exist = remember(item) { item.first.exist() }

            ListItem(
                modifier = Modifier.alpha(if (exist) 1f else .3f),
                headlineContent = {
                    Text(item.second)
                },
                trailingContent = {
                    item.first.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StatusPane()
}