/**
 * Descripción:         Editor de nota rápida
 * Autor:               Leonardo Pachari
 * Fecha de creación:   18/04/2025
 * Última modificación: 18/04/2025
 *
 * Muestra la nota recibida y permite al usuario compartirla
 * o volver a la actividad anterior para seguir editándola.
 */

package com.example.ejercicio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class OpcionesActivity : AppCompatActivity() {

    private lateinit var textViewNote: TextView
    private lateinit var buttonShare: Button
    private lateinit var buttonEdit: Button
    private var noteText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        // Inicializar las vistas
        textViewNote = findViewById(R.id.textViewNote)
        buttonShare = findViewById(R.id.buttonShare)
        buttonEdit = findViewById(R.id.buttonEdit)

        // Recuperar el texto de la nota del intent
        if (savedInstanceState != null) {
            noteText = savedInstanceState.getString("noteText", "")
        } else {
            noteText = intent.getStringExtra("nota") ?: ""
        }

        // Mostrar la nota
        textViewNote.text = noteText

        // Configurar el botón de compartir por correo
        buttonShare.setOnClickListener {
            Toast.makeText(this, "Compartido por correo", Toast.LENGTH_SHORT).show()
        }

        // Configurar el botón de editar de nuevo
        buttonEdit.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("nota_editada", noteText)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    // Guardar el estado de la actividad antes de ser destruida (ej: rotación de pantalla)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("noteText", noteText)
    }
}