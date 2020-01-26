package com.willrussell.manmethacks2

import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {


    var recorder = MediaRecorder()
    var outputFile: String? = null
    var recordingState: Boolean? = false
    var playbackState: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputFile = "${externalCacheDir?.absolutePath}/recording.3gp"

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
        recorder.setOutputFile(outputFile)
    }

    fun record(v: View?) {
        if (recordingState == false) {
            try {
                recorder.prepare()
                recorder.start()
                recordingState = true
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            Toast.makeText(applicationContext, getString(R.string.start_recording), Toast.LENGTH_LONG)
        } else {
            recorder.stop()
            recorder.release()
            Toast.makeText(applicationContext, getString(R.string.finish_recording), Toast.LENGTH_LONG)
            recordingState = false

        }


    }

    fun play(v: View?) {
        if (playbackState == false) {
            val mediaPlayer = MediaPlayer()
            try {
                mediaPlayer.setDataSource(outputFile)
                mediaPlayer.prepare()
                mediaPlayer.start()
                Toast.makeText(applicationContext, getString(R.string.start_playback), Toast.LENGTH_SHORT)

            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
