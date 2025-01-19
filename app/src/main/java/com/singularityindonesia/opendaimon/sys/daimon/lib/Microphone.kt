package com.singularityindonesia.opendaimon.sys.daimon.lib

import android.content.Context
import android.media.MediaRecorder
import com.singularityindonesia.opendaimon.sys.lib.io.AudioRecorder

// todo: turn on and off on demand
class Microphone(
    private val audioRecorder: AudioRecorder
) {
    constructor(mediaRecorder: MediaRecorder): this(AudioRecorder(mediaRecorder))
    constructor(context: Context) : this(MediaRecorder(context))

    init {
        if (audioRecorder.isRecording())
            audioRecorder.stopRecording()
    }

    fun listen() {
        audioRecorder.startRecording()
    }

    fun stop() {
        audioRecorder.stopRecording()
    }
}

