package com.singularityindonesia.constellation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.toOffset
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.singularityindonesia.constellation.Position.Companion.minus
import com.singularityindonesia.constellation.Position.Companion.plus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

typealias NodeId = String

@Immutable
data class Node(
    val id: NodeId,
    val label: String = "",
    val link: Array<NodeId> = emptyArray(),
    val gravity: Float = 0f,
    val repulsion: Float = 0f,
    val position: Position = Position()
)

@Immutable
data class Position(
    val x: Float = 0f,
    val y: Float = 0f,
    val z: Float = 0f
) {
    companion object {
        val ZERO = Position(0f, 0f, 0f)
        infix operator fun Position.plus(other: Position) =
            Position(
                x + other.x,
                y + other.y,
                z + other.z
            )

        infix operator fun Position.minus(other: Position) = Position(
            x - other.x,
            y - other.y,
            z - other.z
        )
    }

    override fun equals(other: Any?): Boolean {
        return (other as? Position)
            ?.minus(this)
            ?.let {
                it.x == ZERO.x &&
                        it.y == ZERO.y &&
                        it.z == ZERO.z
            }
            ?: super.equals(other)
    }

    fun toOffset(): Offset {
        return Offset(
            x,
            y,
        )
    }
}

class Constellation(
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {
    private val celestialBodies = MutableStateFlow(setOf<Node>())
    private val constellationGraph = celestialBodies.map { bodies ->
        bodies
            .map { body ->
                val position = body.position
                val friendPositions = body.link.mapNotNull { friendId ->
                    bodies.firstOrNull { it.id == friendId }?.position
                }

                friendPositions.map { position to it }
            }
            .flatten()
            .fold(emptyArray<Pair<Position, Position>>()) { acc, n ->
                val inverseExist = acc.any { it.second == n.first && it.first == n.second }
                if (inverseExist) {
                    acc
                } else {
                    acc.plus(n)
                }
            }
    }.flowOn(Dispatchers.IO)
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(100), emptyArray())

    fun addNode(node: Node) {
        coroutineScope.launch(Dispatchers.IO) {
            celestialBodies.update { bodies ->
                val nearestFriend = bodies
                    .firstOrNull { body -> body.link.contains(node.id) }
                    ?: node.link.firstOrNull().let { friendId ->
                        bodies.firstOrNull { it.id == friendId }
                    }

                val nearestFriendPosition = nearestFriend?.position ?: Position()
                var position = nearestFriendPosition.plus(
                    Position(
                        Math.random().minus(.5).times(50).toFloat(),
                        Math.random().minus(.5).times(50).toFloat(),
                        Math.random().minus(.5).times(50).toFloat(),
                    )
                )

                while (true) {
                    val positionIsOccupied = bodies
                        .firstOrNull { it.position - position == Position.ZERO } != null

                    if (positionIsOccupied)
                        position = position.plus(
                            Position(
                                x = Math.random().minus(.5).times(50).toFloat(),
                                y = Math.random().minus(.5).times(50).toFloat()
                            )
                        )
                    else break
                }

                bodies.plus(node.copy(position = position))
            }
        }
    }

    @Composable
    fun Draw(
        zoom: Float = 10f
    ) {
        val bodies = celestialBodies.collectAsStateWithLifecycle()
        val graph = constellationGraph.collectAsStateWithLifecycle()

        var scale by remember { mutableStateOf(1f) }
        var offset by remember { mutableStateOf(Offset.Zero) }
        val center = remember { mutableStateOf(Offset.Zero) }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .scale(zoom)
                .onSizeChanged {
                    center.value = it.center.toOffset()
                }
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        scale *= zoom
                        offset += pan
                    }
                }
        ) {
            val maxZ = bodies.value.maxOfOrNull { it.position.z } ?: 0f
            val minZ = bodies.value.minOfOrNull { it.position.z } ?: 0f
            val zRange = maxZ.minus(minZ)

            withTransform({
                translate(offset.x, offset.y)
                scale(scale, scale, center.value)
            }) {
                graph.value.forEach { graph ->
                    drawLine(
                        color = Color.Black,
                        start = center.value + graph.first.toOffset(),
                        end = center.value + graph.second.toOffset(),
                        strokeWidth = 1f / scale / zoom
                    )
                }
                bodies.value.sortedBy { it.position.z }.reversed().forEach { body ->
                    val position = body.position.toOffset()
                    val _scale = (body.position.z - minZ).div(zRange) + 1f
                    drawCircle(
                        color = Color.White.copy(alpha = .6f),
                        radius = 2.2f * _scale,
                        center = center.value + position,
                    )
                    drawCircle(
                        color = Color.Red.copy(alpha = .6f),
                        radius = 2f * _scale,
                        center = center.value + position
                    )
                }
            }
        }
    }
}

@Composable
fun Constellation(
    nodes: Sequence<Node>
) {
    val constellation = remember { Constellation() }

    LaunchedEffect(nodes) {
        nodes.forEach {
            constellation.addNode(it)
        }
    }

    constellation.Draw()
}

@Preview
@Composable
private fun Preview() {
    val nodes = listOf(
        Node(
            id = "1",
            link = arrayOf("5", "8")
        ),
        Node(
            id = "2",
            link = arrayOf("3", "5", "8")
        ),
        Node(
            id = "3",
            link = arrayOf("5")
        ),
        Node(
            id = "4",
            link = arrayOf("3", "2")
        ),
        Node(
            id = "5",
            link = arrayOf("4", "1")
        ),
        Node(
            id = "6",
            link = arrayOf("5", "7")
        ),
        Node(
            id = "7",
            link = arrayOf("8")
        ),
        Node(
            id = "8",
            link = arrayOf("5")
        ),
        Node(
            id = "9",
            link = arrayOf("5")
        ),
        Node(
            id = "10",
            link = arrayOf("8")
        )
    )

    Constellation(
        nodes = nodes.asSequence()
    )
}