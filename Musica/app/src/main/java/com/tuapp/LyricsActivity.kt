package com.tuapp.musica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LyricsActivity : AppCompatActivity() {

    private lateinit var lyricsEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        lyricsEditText = findViewById(R.id.lyricsEditText)
        saveButton = findViewById(R.id.saveButton)

        // Configuración del botón para guardar la letra
        saveButton.setOnClickListener {
            val letra = lyricsEditText.text.toString().trim()  // Quitar espacios extra al inicio y final

            // Validar que la letra no esté vacía
            if (letra.isNotEmpty()) {
                saveLyrics(letra)  // Llamar al método para guardar la letra
            } else {
                // Mostrar un mensaje de error si la letra está vacía
                Toast.makeText(this, "Por favor, escribe la letra antes de guardar.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveLyrics(letra: String) {
        // Inicializar Firebase Database
        val database: DatabaseReference = FirebaseDatabase.getInstance().reference

        // Crear un nuevo ID único para la letra y guardarla en Firebase
        val lyricsId = database.child("lyrics").push().key

        // Verificar si se ha generado un ID único
        if (lyricsId != null) {
            // Guardar la letra en Firebase bajo el ID generado
            database.child("lyrics").child(lyricsId).setValue(letra)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Mostrar mensaje de éxito si se guarda correctamente
                        Toast.makeText(this, "Letra guardada con éxito!", Toast.LENGTH_SHORT).show()
                        // Limpiar el campo de texto después de guardar
                        lyricsEditText.text.clear()
                    } else {
                        // Mostrar mensaje de error si algo salió mal
                        Toast.makeText(this, "Error al guardar la letra", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    // Manejo de error en caso de falla
                    Toast.makeText(this, "Fallo al guardar la letra: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            // Mostrar un mensaje en caso de que no se genere un ID
            Toast.makeText(this, "Error al generar ID para la letra", Toast.LENGTH_SHORT).show()
        }
    }
}
