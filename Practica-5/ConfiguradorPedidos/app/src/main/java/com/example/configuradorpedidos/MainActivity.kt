/**
 * Actividad principal de la aplicación de configuración de pedidos
 * Autor: Leonardo Pachari
 * Fecha creación: 23/04/2025
 * Última modificación: 24/04/2025
 */

package com.example.configuradorpedidos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Si necesitamos configurar la barra de herramientas
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Configuración adicional si se requiere
    }
}