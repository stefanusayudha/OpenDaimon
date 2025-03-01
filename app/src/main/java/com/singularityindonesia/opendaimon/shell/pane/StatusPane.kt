package com.singularityindonesia.opendaimon.shell.pane

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.singularityindonesia.opendaimon.lib.sys.LocalSensor

// todo: should observe the daimon directly
@Composable
fun StatusPane(
    modifier: Modifier = Modifier,
    goToNeuronGraph: () -> Unit = {}
) {
    val sensors = LocalSensor.current

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
//        item {
//            ListItem(
//                headlineContent = {
//                    Text("Microphone")
//                },
//                trailingContent = {
//                    daimon.microphone.Display()
//                }
//            )
//        }
//        item {
//            ListItem(
//                headlineContent = {
//                    Text("Usb Device")
//                },
//                trailingContent = {
//                    daimon.serialBus.Display()
//                }
//            )
//        }
        item {
            ListItem(
                headlineContent = {
                    Text("Neurons Count")
                },
                trailingContent = {
                    Row(
                        modifier = Modifier.offset {
                            IntOffset(16.dp.roundToPx(), 0)
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // todo: display neurons count
                        Text("0")
                        IconButton(
                            onClick = goToNeuronGraph
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }

                },
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