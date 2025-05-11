/*
 * Juego de Colores - Clase para gestionar los colores del juego
 * Autor: Leonardo Pachari
 * Fecha creación: 11/05/2025
 * Última modificación: 11/05/2025
 */

package com.example.juegodecolores

import android.content.Context
import androidx.core.content.ContextCompat

class ColorManager(private val context: Context) {
    
    // Definición de los colores disponibles en el juego
    val colorMap = mapOf(
        R.color.red to R.string.red,
        R.color.green to R.string.green,
        R.color.blue to R.string.blue,
        R.color.yellow to R.string.yellow,
        R.color.purple to R.string.purple,
        R.color.orange to R.string.orange
    )
    
    // Botones del juego con sus respectivos colores
    val buttonColorMap = mapOf(
        R.id.redButton to R.color.red,
        R.id.greenButton to R.color.green,
        R.id.blueButton to R.color.blue,
        R.id.yellowButton to R.color.yellow,
        R.id.purpleButton to R.color.purple,
        R.id.orangeButton to R.color.orange
    )
    
    // Color actual que se muestra en pantalla
    var currentColor = R.color.red
    
    // Método para obtener un color aleatorio
    fun getRandomColor(): Int {
        val colors = colorMap.keys.toList()
        val randomIndex = (0 until colors.size).random()
        return colors[randomIndex]
    }
    
    // Método para obtener el nombre del color
    fun getColorName(colorResId: Int): String {
        val stringResId = colorMap[colorResId] ?: R.string.red
        return context.getString(stringResId)
    }
    
    // Método para obtener el valor del color
    fun getColorValue(colorResId: Int): Int {
        return ContextCompat.getColor(context, colorResId)
    }
}