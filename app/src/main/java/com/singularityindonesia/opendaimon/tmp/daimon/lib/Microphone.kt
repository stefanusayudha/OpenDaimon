package com.singularityindonesia.opendaimon.tmp.daimon.lib

import android.content.Context
import android.media.MediaRecorder
import android.os.Environment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.singularityindonesia.opendaimon.lib.io.AudioRecorder
import java.io.File

// todo: should be attached to the daimon
// todo: should be started and stopped on demand
class Microphone(
    private val audioRecorder: AudioRecorder
) {
    constructor(mediaRecorder: MediaRecorder, output: File) : this(
        AudioRecorder(
            mediaRecorder,
            output
        )
    )

    constructor(context: Context) : this(
        MediaRecorder(context), File(
            context.getExternalFilesDir(
                Environment.DIRECTORY_MUSIC
            ), "out.au"
        )
    )

    init {
        if (audioRecorder.isRecording.value)
            audioRecorder.stopRecording()
    }

    val isListening = audioRecorder.isRecording

    // fixme: limit file size or duration or data size
    fun listen() {
        audioRecorder.startRecording()
    }

    fun isListening(): Boolean {
        return audioRecorder.isRecording.value
    }

    fun stop() {
        audioRecorder.stopRecording()
    }

    @Composable
    fun Display(modifier: Modifier = Modifier) {
        val isListening = isListening.collectAsStateWithLifecycle()

        // fixme: should be started by the daimon
        // LaunchedEffect(isListening.value) {
        //     if (isListening.value) return@LaunchedEffect
        //
        //     listen()
        // }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(
                        if (isListening.value) Color.Green
                        else Color.Red
                    )
            )
        }
    }
}

