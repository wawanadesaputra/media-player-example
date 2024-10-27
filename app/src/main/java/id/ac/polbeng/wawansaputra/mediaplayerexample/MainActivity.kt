package id.ac.polbeng.wawansaputra.mediaplayerexample

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.ac.polbeng.wawansaputra.mediaplayerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private var pause = false
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            if (pause) {
                // Initially, pause is set to false
                mediaPlayer?.seekTo(position)
                mediaPlayer?.start()
                pause = false // Playing audio when in paused state
            } else {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(applicationContext, R.raw.sound)
                    mediaPlayer?.setOnCompletionListener { stopPlayer() }
                }
                mediaPlayer?.start() // Playing audio when in prepared state
            }
        }

        binding.btnPause.setOnClickListener {
            if (mediaPlayer != null) {
                position = mediaPlayer!!.currentPosition
                mediaPlayer?.pause()
                pause = true
            }
        }

        binding.btnStop.setOnClickListener {
            stopPlayer()
        }
    }

    private fun stopPlayer() {
        if (mediaPlayer != null) {
            pause = false
            position = 0
            mediaPlayer?.release()
            mediaPlayer = null
            Toast.makeText(this, "Media Player Released!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
    }
}
