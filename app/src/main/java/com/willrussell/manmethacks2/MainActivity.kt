package com.willrussell.manmethacks2

import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.media.PlaybackParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {


    var recorder = MediaRecorder()
    var outputFile: String? = null
    var recordingState: Boolean? = false
    var playbackState: Boolean? = false
    var pitchBar: SeekBar? = null
    var speedBar: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pitchBar = findViewById(R.id.pitch)
        speedBar = findViewById(R.id.speed)

        outputFile = "${externalCacheDir?.absolutePath}/recording.3gp"
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
        recorder.setAudioSamplingRate(440000)
        recorder.setAudioEncodingBitRate(96000)
        recorder.setOutputFile(outputFile)
    }

    fun record(v: View?) {
        if (recordingState == false) {
            System.out.println("Recording == false")
            try {
                recorder.prepare()
                recorder.start()
                recordingState = true
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            Toast.makeText(applicationContext, getString(R.string.start_recording), Toast.LENGTH_LONG).show()
        } else {
            System.out.println("Recording stop")
            recorder.stop()
            recorder.release()
            Toast.makeText(applicationContext, getString(R.string.finish_recording), Toast.LENGTH_LONG).show()
            recordingState = false

        }


    }

    fun play(v: View?) {
        try {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(outputFile)
            System.out.println(speedBar?.progress)
            mediaPlayer.prepare()
            var params = PlaybackParams()
            params.setPitch(pitchBar?.progress!!.toFloat() / 50)
            params.setSpeed(speedBar?.progress!!.toFloat() / 50)
            mediaPlayer.playbackParams = params
            mediaPlayer.start()
            Toast.makeText(applicationContext, getString(R.string.start_playback), Toast.LENGTH_SHORT).show()
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

}
