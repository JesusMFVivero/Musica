package com.tuapp.musica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LyricsActivity : AppCompatActivity() {

    private lateinit var lyricsEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        lyricsEditText = findViewById(R.id.lyricsEditText)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val letra = lyricsEditText.text.toString()
            // Guardar la letra (puedes usar Firebase o cualquier base de datos local)
            saveLyrics(letra)
        }
    }

    private fun saveLyrics(letra: String) {
       
