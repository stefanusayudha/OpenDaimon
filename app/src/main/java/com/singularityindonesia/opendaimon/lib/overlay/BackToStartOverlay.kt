package com.singularityindonesia.opendaimon.lib.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class BackDirection {
    RIGHT, LEFT
}

@Composable
fun BackToStartOverLay(
    showButton: Boolean = true,
    backDirection: BackDirection = BackDirection.LEFT,
    onBackToStart: () -> Unit
) {
    if (showButton)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SmallFloatingActionButton(
                modifier = Modifier.align(
                    if (backDirection == BackDirection.LEFT)
                        Alignment.BottomStart
                    else
                        Alignment.BottomEnd
                ),
                onClick = onBackToStart
            ) {
                Icon(
                    imageVector = if (backDirection == BackDirection.LEFT)
                        Icons.AutoMirrored.Rounded.KeyboardArrowLeft
                    else
                        Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = ""
                )
            }
        }
}