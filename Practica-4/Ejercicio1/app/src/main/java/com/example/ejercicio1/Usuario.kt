/**
Descripción:                    Actividad para recibir los datos del perfil de usuario
Autor:                          Leonardo Pachari
Fecha de creación:              17/04/25
Fecha última de modificación:   17/04/25
 */

package com.example.ejercicio1
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val nombre: String,
    val edad: Int,
    val ciudad: String,
    val correo: String
) : Parcelable
