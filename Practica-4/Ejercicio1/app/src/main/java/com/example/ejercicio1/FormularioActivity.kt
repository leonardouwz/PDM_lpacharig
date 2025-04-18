/**
Descripción:                    Actividad para recibir los datos del perfil de usuario
Autor:                          Leonardo Pachari
Fecha de creación:              17/04/25
Fecha última de modificación:   17/04/25
 */

package com.example.ejercicio1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etCiudad: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnContinuar: Button

    // Constantes para guardar el estado
    companion object {
        private const val NOMBRE_KEY = "nombre"
        private const val EDAD_KEY = "edad"
        private const val CIUDAD_KEY = "ciudad"
        private const val CORREO_KEY = "correo"
    }

    // Registro para recibir resultados de la segunda actividad
    private val resumenLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Si el usuario confirma, mostrar el mensaje y limpiar campos
            Toast.makeText(this, "Perfil guardado correctamente", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        }
        // Si cancela, los campos mantienen sus valores para edición
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etCiudad = findViewById(R.id.etCiudad)
        etCorreo = findViewById(R.id.etCorreo)
        btnContinuar = findViewById(R.id.btnContinuar)

        // Restaurar estado si existe
        savedInstanceState?.let {
            etNombre.setText(it.getString(NOMBRE_KEY, ""))
            etEdad.setText(it.getString(EDAD_KEY, ""))
            etCiudad.setText(it.getString(CIUDAD_KEY, ""))
            etCorreo.setText(it.getString(CORREO_KEY, ""))
        }

        // Configurar el botón continuar
        btnContinuar.setOnClickListener {
            val usuario = Usuario(
                nombre = etNombre.text.toString(),
                edad = etEdad.text.toString().toIntOrNull() ?: 0,
                ciudad = etCiudad.text.toString(),
                correo = etCorreo.text.toString()
            )

            val intent = Intent(this, ResumenActivity::class.java)
            intent.putExtra("usuario", usuario)
            resumenLauncher.launch(intent)
        }
    }

    // Guardar estado en caso de rotación de pantalla
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NOMBRE_KEY, etNombre.text.toString())
        outState.putString(EDAD_KEY, etEdad.text.toString())
        outState.putString(CIUDAD_KEY, etCiudad.text.toString())
        outState.putString(CORREO_KEY, etCorreo.text.toString())
    }

    private fun limpiarCampos() {
        etNombre.text.clear()
        etEdad.text.clear()
        etCiudad.text.clear()
        etCorreo.text.clear()
    }
}