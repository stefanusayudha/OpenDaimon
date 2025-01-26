package com.singularityindonesia.opendaimon.shell.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import com.singularityindonesia.opendaimon.lib.component.HeaderPage
import com.singularityindonesia.opendaimon.lib.overlay.BackDirection
import com.singularityindonesia.opendaimon.lib.overlay.BackToStartOverLay
import com.singularityindonesia.opendaimon.shell.home.scene.GlyphScene
import com.singularityindonesia.opendaimon.shell.system.pane.AboutPane
import com.singularityindonesia.opendaimon.shell.system.pane.ControlPane
import com.singularityindonesia.opendaimon.shell.system.pane.SerialMonitorPane
import com.singularityindonesia.opendaimon.shell.system.pane.StatusPane
import kotlinx.coroutines.launch

@Composable
fun HomeActivity(
    goToScanProtocol: () -> Unit = {},
    goToNeuronGraph: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 1) { 5 }

    BackHandler(
        enabled = pagerState.currentPage != 1
    ) {
        scope.launch {
            if (pagerState.currentPage > 1)
                pagerState.animateScrollToPage(pagerState.currentPage - 1)

            if (pagerState.currentPage < 1)
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .systemBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            AnimatedVisibility(
                visible =pagerState.currentPage != 1,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                HeaderPage(
                    titleText = when (pagerState.currentPage) {
                        0 -> "Monitor"
                        1 -> ""
                        2 -> "Status"
                        3 -> "Control"
                        4 -> "About"
                        else -> ""
                    }
                )
            }
        }
    ) { padding ->
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            state = pagerState
        ) { index ->
            when (index) {
                0 -> SerialMonitorPane()
                1 -> GlyphScene()
                2 -> StatusPane(
                    goToNeuronGraph = goToNeuronGraph
                )
                3 -> ControlPane(
                    goToScanProtocol = goToScanProtocol,
                    goToNeuronGraph = goToNeuronGraph
                )

                4 -> AboutPane()
            }
        }

        BackToStartOverLay(
            showButton = pagerState.currentPage > 1 || pagerState.currentPage < 1,
            backDirection = if (pagerState.currentPage < 1)
                BackDirection.RIGHT
            else
                BackDirection.LEFT
        ) {
            scope.launch {
                pagerState.animateScrollToPage(1)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeActivity()
}