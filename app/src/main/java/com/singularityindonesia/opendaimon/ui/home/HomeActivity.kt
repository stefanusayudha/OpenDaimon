package com.singularityindonesia.opendaimon.ui.home

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
import com.singularityindonesia.opendaimon.ui.component.HeaderPage
import com.singularityindonesia.opendaimon.ui.overlay.BackToStartOverLay
import kotlinx.coroutines.launch

@Composable
fun HomeActivity(
    goToScanProtocol: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 4 }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .systemBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            if (pagerState.currentPage < 1) return@Scaffold
            HeaderPage(
                titleText = when (pagerState.currentPage) {
                    1 -> "Control"
                    2 -> "Logs"
                    3 -> "About"
                    else -> ""
                },
                backAction = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
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
                1 -> ControlPane(
                    goToScanProtocol = goToScanProtocol
                )

                2 -> LogsPane()
                3 -> AboutPane()
            }
        }

        BackToStartOverLay(
            showButton = pagerState.currentPage > 1
        ) {
            scope.launch {
                pagerState.animateScrollToPage(0)
            }
        }
    }
}