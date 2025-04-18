/**
Descripción:                    Actividad para mostrar resumen de datos del usuario y confirmar
Autor:                          Leonardo Pachari
Fecha de creación:              17/04/25
Fecha última de modificación:   17/04/25
 */

package com.example.ejercicio1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResumenActivity : AppCompatActivity() {

    private lateinit var tvNombre: TextView
    private lateinit var tvEdad: TextView
    private lateinit var tvCiudad: TextView
    private lateinit var tvCorreo: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        // Inicializar vistas
        tvNombre = findViewById(R.id.tvNombre)
        tvEdad = findViewById(R.id.tvEdad)
        tvCiudad = findViewById(R.id.tvCiudad)
        tvCorreo = findViewById(R.id.tvCorreo)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnEditar = findViewById(R.id.btnEditar)

        // Obtener datos del intent
        val usuario = intent.getParcelableExtra<Usuario>("usuario")

        // Mostrar datos
        usuario?.let {
            tvNombre.text = "Nombre: ${it.nombre}"
            tvEdad.text = "Edad: ${it.edad}"
            tvCiudad.text = "Ciudad: ${it.ciudad}"
            tvCorreo.text = "Correo: ${it.correo}"
        }

        // Configurar botones
        btnConfirmar.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        btnEditar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}