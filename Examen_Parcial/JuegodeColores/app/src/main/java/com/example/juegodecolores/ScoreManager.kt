/*
 * Juego de Colores - Clase para gestionar los puntajes usando SharedPreferences
 * Autor: Leonardo Pachari
 * Fecha creación: 11/05/2025
 * Última modificación: 11/05/2025
 */

package com.example.juegodecolores

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class ScoreManager(context: Context) {

    // Constantes para SharedPreferences
    companion object {
        private const val PREF_NAME = "color_game_prefs"
        private const val HIGH_SCORE_KEY = "high_score"
    }

    // SharedPreferences
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREF_NAME, Context.MODE_PRIVATE
    )

    // Función para obtener la puntuación más alta
    fun getHighScore(): Int {
        return sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
    }

    // Función para guardar la puntuación más alta
    fun saveHighScore(score: Int): Boolean {
        val currentHighScore = getHighScore()

        // Solo guardamos si la nueva puntuación es mayor que la existente
        return if (score > currentHighScore) {
            sharedPreferences.edit() { putInt(HIGH_SCORE_KEY, score) }
            true // Indicamos que se ha actualizado el récord
        } else {
            false // No se ha actualizado
        }
    }

    // Función para reiniciar la puntuación más alta
    fun resetHighScore() {
        sharedPreferences.edit() { remove(HIGH_SCORE_KEY) }
    }
}