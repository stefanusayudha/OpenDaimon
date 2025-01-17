package com.singularityindonesia.opendaimon.ui.pane

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularityindonesia.opendaimon.BuildConfig
import com.singularityindonesia.opendaimon.R

@Composable
fun AboutPane(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(200.dp),
                painter = painterResource(R.drawable.lilith_banner),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                textAlign = TextAlign.Center,
                text = "Code Name: ${BuildConfig.CODE_NAME}",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                textAlign = TextAlign.Center,
                text = BuildConfig.APP_NAME,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                textAlign = TextAlign.Center,
                text = "Version: ${BuildConfig.VERSION_NAME}",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(48.dp))
        }

        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Text(
                text = "Powered by: ${BuildConfig.COMPANY_NAME}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AboutPane()
}