package com.tuapp.musica

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class MainActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer
    private lateinit var songTitle: TextView
    private lateinit var playButton: Button
    private lateinit var lyricsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songTitle = findViewById(R.id.songTitle)
        playButton = findViewById(R.id.playButton)
        lyricsButton = findViewById(R.id.lyricsButton)

        // Configurar ExoPlayer
        player = SimpleExoPlayer.Builder(this).build()

        val mediaItem = MediaItem.fromUri("file:///android_asset/tu_cancion.mp3") // Ruta local
        player.setMediaItem(mediaItem)

        playButton.setOnClickListener {
            player.prepare()
            player.play()
        }

        lyricsButton.setOnClickListener {
            val intent = Intent(this, LyricsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }
}
