package com.singularityindonesia.opendaimon.shell.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.singularityindonesia.opendaimon.shell.lib.component.HeaderPage
import com.singularityindonesia.opendaimon.shell.lib.overlay.BackToStartOverLay
import com.singularityindonesia.opendaimon.shell.system.AboutPane
import com.singularityindonesia.opendaimon.shell.system.ControlPane
import com.singularityindonesia.opendaimon.shell.system.StatusPane
import kotlinx.coroutines.launch

@Composable
fun HomeActivity(
    goToScanProtocol: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 4 }

    BackHandler(
        enabled = pagerState.currentPage != 0
    ) {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .systemBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            if (pagerState.currentPage < 1) return@Scaffold
            HeaderPage(
                titleText = when (pagerState.currentPage) {
                    1 -> "Status"
                    2 -> "Control"
                    3 -> "About"
                    else -> ""
                }
            )
        }
    ) { padding ->
        HorizontalPager(
            modifier = Modifier.padding(padding),
            state = pagerState
        ) { index ->
            when (index) {
                0 -> GlyphPane()
                1 -> StatusPane()
                2 -> ControlPane(
                    goToScanProtocol = goToScanProtocol
                )

                3 -> AboutPane()
            }
        }

        BackToStartOverLay(
            showButton = pagerState.currentPage > 0
        ) {
            scope.launch {
                pagerState.animateScrollToPage(0)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeActivity(
        goToScanProtocol = {}
    )
}