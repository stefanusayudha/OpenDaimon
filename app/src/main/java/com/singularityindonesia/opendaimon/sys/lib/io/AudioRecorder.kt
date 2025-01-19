package com.singularityindonesia.opendaimon.sys.lib.io

import android.media.MediaRecorder
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

// todo: turn on and off on demand
class AudioRecorder(
    private val mediaRecorder: MediaRecorder,
    private val outputFile: File
) {
    val isRecording = MutableStateFlow(false)
    var recordingThread: HandlerThread? = null
    var recordingHandler: Handler? = null

    fun startRecording() {
        if (isRecording.value) return
        try {
            // Create a HandlerThread for recording
            recordingThread = HandlerThread("RecordingThread")
            recordingThread?.start()

            // Get the Looper from the HandlerThread
            val looper = recordingThread?.looper

            // Create a Handler for the Looper
            recordingHandler = Handler(looper!!)

            // Post the recording task to the Handler
            recordingHandler?.post {
                mediaRecorder.apply {
                    reset()
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                    setOutputFile(outputFile)
                    prepare()
                    start()
                }
                isRecording.value = true
                Log.d("AudioRecorder", "Recording started")
            }
        } catch (e: Exception) {
            Log.e("AudioRecorder", "Error starting recording: ${e.message}")
            stopRecording()
        }
    }

    fun stopRecording() {
        if (!isRecording.value) return
        try {
            recordingHandler?.post {
                mediaRecorder.apply {
                    // Stop the MediaRecorder
                    stop()
                    // Release the MediaRecorder
                    release()
                    // Reset the MediaRecorder
                    reset()
                }
                isRecording.value = false
                Log.d("AudioRecorder", "Recording stopped")
            }
        } catch (e: Exception) {
            Log.e("AudioRecorder", "Error stopping recording: ${e.message}")
        } finally {
            recordingThread?.quitSafely()
        }
    }
}