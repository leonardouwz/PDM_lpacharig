/**
 * Descripción:         Editor de nota rápida - Actividad principal
 * Autor:               Leonardo Pachari
 * Fecha de creación:   18/04/2025
 * Última modificación: 18/04/2025
 *
 * Permite al usuario escribir una nota y enviarla a otra actividad
 * para elegir entre compartirla o volver a editar.
 */

package com.example.ejercicio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts

class EditorActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var buttonShare: Button
    private var noteText = ""

    // Registro del callback para recibir resultados de la actividad OpcionesActivity
    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Recuperar el texto de la nota que fue devuelto
            val receivedNote = result.data?.getStringExtra("nota_editada") ?: ""
            editTextNote.setText(receivedNote)
            // Posicionar el cursor al final del texto
            editTextNote.setSelection(receivedNote.length)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        // Inicializar las vistas
        editTextNote = findViewById(R.id.editTextNote)
        buttonShare = findViewById(R.id.buttonShare)

        // Restaurar el texto de la nota si existe un estado guardado
        if (savedInstanceState != null) {
            noteText = savedInstanceState.getString("noteText", "")
            editTextNote.setText(noteText)
        }

        // Configurar el botón de compartir
        buttonShare.setOnClickListener {
            // Obtener el texto de la nota
            noteText = editTextNote.text.toString()

            // Crear un intent para iniciar la actividad OpcionesActivity
            val intent = Intent(this, OpcionesActivity::class.java)
            intent.putExtra("nota", noteText)

            // Iniciar la actividad esperando un resultado
            getResult.launch(intent)
        }
    }

    // Guardar el estado de la actividad antes de ser destruida (ej: rotación de pantalla)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("noteText", editTextNote.text.toString())
    }
}