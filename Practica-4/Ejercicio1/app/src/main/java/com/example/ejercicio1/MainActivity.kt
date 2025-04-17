/**
Descripción:                    Actividad de inicio del programa
Autor:                          Leonardo Pachari
Fecha de creación:              15/04/25
Fecha última de modificación:   17/04/25
 */

package com.example.ejercicio1

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnComenzar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnComenzar = findViewById(R.id.btn_Comenzar)

        btnComenzar.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }
}