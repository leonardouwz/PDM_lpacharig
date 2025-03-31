/**
Descripción del problema:       Se desea una aplicación que muestre una imagen al usuario y le permita hacer clic en ella, mostrando un mensaje Toast.
Autor:                          Leonardo Pachari
Fecha de creación:              29/03/25
Fecha última de modificación:   30/03/25
 */

package com.example.practica3_ejercicio1

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Encontrar la ImageView por su ID
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Configurar un listener para el clic
        imageView.setOnClickListener {
            // Mostrar un mensaje Toast al hacer clic
            Toast.makeText(this,
                "¡Has hecho clic en la imagen!",
                Toast.LENGTH_SHORT).show()
        }
    }
}