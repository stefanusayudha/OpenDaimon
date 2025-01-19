package com.singularityindonesia.opendaimon.sys.lib.io

import android.media.MediaRecorder
import android.os.Looper
import android.util.Log
import java.io.File

// todo: turn on and off on demand
class AudioRecorder(
    private val mediaRecorder: MediaRecorder
) {
    private val outputFile = File("/out.au")
    private var isRecording = false

    fun startRecording() {
        if (isRecording) return

        try {
            mediaRecorder.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(outputFile)
                Looper.prepare()
                start()
            }
            isRecording = true
            Log.d("AudioRecorder", "Recording started")
        } catch (e: Exception) {
            Log.e("AudioRecorder", "Error starting recording: ${e.message}")
            stopRecording()
        }
    }

    fun stopRecording() {
        if (!isRecording) return

        try {
            mediaRecorder.apply {
                stop()
                release()
            }
            isRecording = false
            Log.d("AudioRecorder", "Recording stopped")
        } catch (e: Exception) {
            Log.e("AudioRecorder", "Error stopping recording: ${e.message}")
        }
    }

    fun isRecording(): Boolean {
        return isRecording
    }
}