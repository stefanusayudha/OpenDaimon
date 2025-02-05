package com.singularityindonesia.opendaimon.shell.pane

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.singularityindonesia.opendaimon.lib.overlay.BackDirection
import com.singularityindonesia.opendaimon.lib.overlay.BackToStartOverLay
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HomePane(
    goToScanProtocol: () -> Unit = {},
    goToNeuronGraph: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 1) { 5 }

    val showTopBar = remember { mutableStateOf(false) }
    DisposableEffect(pagerState.currentPage) {
        val job = scope.launch {
            if (pagerState.currentPage == 1) {
                delay(2000L)
                showTopBar.value = false
            } else {
                showTopBar.value = true
            }
        }

        onDispose { job.cancel() }
    }

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

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .systemBarsPadding()
            .navigationBarsPadding(),
    ) {
        Column {
            AnimatedVisibility(
                visible = showTopBar.value,
            ) {
                TabRow(selectedTabIndex = pagerState.currentPage) {
                    (0..4).map {
                        Tab(
                            selected = it == pagerState.currentPage,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(it)
                                }
                            },
                            text = {
                                when (it) {
                                    0 -> Text("Serial")
                                    1 -> Text("Glyph")
                                    2 -> Text("Status")
                                    3 -> Text("Control")
                                    4 -> Text("About")
                                }
                            }
                        )
                    }
                }
            }

            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = pagerState
            ) { index ->
                when (index) {
                    0 -> SerialMonitorPane()
                    1 -> GlyphPane()
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
    HomePane()
}