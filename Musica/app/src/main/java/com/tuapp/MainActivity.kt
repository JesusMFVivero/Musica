package com.tuapp.musica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

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

        // Cargar canción desde los assets
        val mediaItem = MediaItem.fromUri("asset:///tu_cancion.mp3")  // Usar asset:// para cargar desde assets
        player.setMediaItem(mediaItem)

        playButton.setOnClickListener {
            if (player.isPlaying) {
                player.pause()  // Pausar si ya está en reproducción
                playButton.text = "Play"  // Cambiar texto del botón a "Play"
            } else {
                player.prepare()  // Preparar el reproductor
                player.play()  // Iniciar la reproducción
                playButton.text = "Pause"  // Cambiar texto del botón a "Pause"
            }
        }

        lyricsButton.setOnClickListener {
            val intent = Intent(this, LyricsActivity::class.java)
            // Puedes pasar información sobre la canción aquí
            intent.putExtra("songTitle", "Tu Canción") // Ejemplo de paso de dato
            startActivity(intent)  // Iniciar la actividad de letras
        }
    }

    override fun onStop() {
        super.onStop()
        // Liberar recursos del ExoPlayer cuando la actividad se detiene
        player.release()
    }
}
