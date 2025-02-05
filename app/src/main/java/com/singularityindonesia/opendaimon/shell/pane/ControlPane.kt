package com.singularityindonesia.opendaimon.shell.pane

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun ControlPane(
    modifier: Modifier = Modifier,
    goToScanProtocol: () -> Unit = {},
    goToNeuronGraph: () -> Unit = {}
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        item {
            ControlItem(
                text = "Scan Protocol",
                action = goToScanProtocol
            )
        }
        item {
            ControlItem(
                text = "Neuron Graph",
                action = goToNeuronGraph
            )
        }
    }
}

@Composable
private fun ControlItem(
    text: String,
    action: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        trailingContent = {
            IconButton(
                modifier = Modifier.offset {
                    IntOffset(16.dp.roundToPx(), 0)
                },
                onClick = action
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = "",
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    ControlPane()
}