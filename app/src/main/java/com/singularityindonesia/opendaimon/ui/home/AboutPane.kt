package com.singularityindonesia.opendaimon.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Image(
            modifier = Modifier.height(200.dp),
            painter = painterResource(R.drawable.lilith_banner),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            textAlign = TextAlign.Center,
            text = "${BuildConfig.APP_NAME} v${BuildConfig.VERSION_NAME}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            textAlign = TextAlign.Center,
            text = BuildConfig.CODE_NAME,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.weight(3f))
        Text(
            text = "Powered by: ${BuildConfig.COMPANY_NAME}",
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Preview
@Composable
private fun Preview() {
    AboutPane()
}