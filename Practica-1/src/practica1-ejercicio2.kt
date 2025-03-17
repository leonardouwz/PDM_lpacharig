/**
    Descripción del problema:       Se desea un juego de piedra papel y tijeras donde la computadora elija de forma aleatoria
    Autor:                          Leonardo Pachari
    Fecha de creación:              16/03/25
    Fecha última de modificación:   16/03/25
 */

import kotlin.random.Random

// Función principal del juego de Piedra, Papel y Tijeras
fun piedraPapelTijeras(jugadaUsuario: Int) {
    // Mapeo de valores numéricos a nombres de jugadas
    val opciones = mapOf(1 to "Piedra", 2 to "Papel", 3 to "Tijera")

    // Generar una jugada aleatoria para la computadora
    val jugadaComputadora = Random.nextInt(1, 4)

    // Lógica de decisión del ganador basada en las reglas del juego
    val resultado = when {
        // Condiciones de victoria del jugador
        (jugadaUsuario == 1 && jugadaComputadora == 3) || // Piedra vence a Tijeras
                (jugadaUsuario == 2 && jugadaComputadora == 1) || // Papel vence a Piedra
                (jugadaUsuario == 3 && jugadaComputadora == 2)    // Tijeras vence a Papel
            -> "¡Ganaste!"

        jugadaUsuario == jugadaComputadora -> "Empataste"
        else -> "Perdiste"
    }

    // Mostrar resultados y jugadas
    println("\n$resultado \n")
    println("La computadora ha elegido: ${opciones[jugadaComputadora]}")
    println("Tu jugada: ${opciones[jugadaUsuario]}")
}

fun main() {
    // Mostrar instrucciones y opciones del juego
    println("Bienvenido al juego de Piedra, Papel y Tijeras")
    println("1. Piedra")
    println("2. Papel")
    println("3. Tijeras")

    // Solicitar la elección del usuario
    print("Elige una opción: ")
    val jugada = readln().toInt()

    // Validación de entrada
    if (jugada > 3) {
        println("Hasta luego")
        return
    }

    // Llamar a la función principal del juego
    piedraPapelTijeras(jugada)
}