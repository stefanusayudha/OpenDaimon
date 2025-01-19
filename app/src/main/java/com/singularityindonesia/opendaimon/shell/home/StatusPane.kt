package com.singularityindonesia.opendaimon.shell.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.singularityindonesia.opendaimon.sys.lib.sensor.LocalSensors

@Composable
fun StatusPane(
    modifier: Modifier = Modifier
) {
    val sensors = LocalSensors.current
    val accelerometer = sensors.accelerometer
    val gravity = sensors.gravity
    val gyroscope = sensors.gyroscope
    val linearAcceleration = sensors.linearAcceleration
    val rotation = sensors.rotation
    val geoRotation = sensors.geoRotation
    val proximity = sensors.proximity
    val light = sensors.light
    val ambienceTemp = sensors.ambienceTemp
    val pressure = sensors.pressure
    val humidity = sensors.humidity

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        item {
            ListItem(
                headlineContent = {
                    Text("Accelerometer")
                },
                trailingContent = {
                    accelerometer.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Gravity")
                },
                trailingContent = {
                    gravity.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Gyro")
                },
                trailingContent = {
                    gyroscope.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Rotation")
                },
                trailingContent = {
                    rotation.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Geo Rotation")
                },
                trailingContent = {
                    geoRotation.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Linear Accel")
                },
                trailingContent = {
                    linearAcceleration.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Proximity")
                },
                trailingContent = {
                    proximity.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Light")
                },
                trailingContent = {
                    light.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Ambience Temp")
                },
                trailingContent = {
                    ambienceTemp.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Pressure")
                },
                trailingContent = {
                    pressure.Display(
                        modifier = Modifier,
                        alignment = Alignment.End
                    )
                },
            )
        }

        item {
            ListItem(
                headlineContent = {
                    Text("Humidity")
                },
                trailingContent = {
                    humidity.Display(
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