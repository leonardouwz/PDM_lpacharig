/**
 * Modelo de datos para el pedido
 * Autor: Leonardo Pachari
 * Fecha creación: 24/04/2025
 * Última modificación: 24/04/2025
 */

package com.example.configuradorpedidos.model

import java.io.Serializable
data class Pedido(
    var comida: String = "",
    var extras: MutableList<String> = mutableListOf()
) : Serializable