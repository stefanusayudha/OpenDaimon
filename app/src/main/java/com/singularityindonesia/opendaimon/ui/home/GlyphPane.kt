package com.singularityindonesia.opendaimon.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GlyphPane(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
    }
}