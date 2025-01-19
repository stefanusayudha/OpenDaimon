package com.singularityindonesia.opendaimon.shell.system

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
import androidx.compose.ui.tooling.preview.Preview
import com.singularityindonesia.opendaimon.sys.lib.sensor.LocalSensors

@Composable
fun StatusPane(
    modifier: Modifier = Modifier
) {
    val sensors = LocalSensors.current
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
                ).filter { it.first.exist() }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        items(items.size) { i ->
            val item = items[i]

            ListItem(
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