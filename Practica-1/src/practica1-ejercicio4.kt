/**
    Descripción del problema:       Se desea una programa donde se tenga que adivinar un número del 1 al 30 en 5 intentos con pistas
    Autor:                          Leonardo Pachari
    Fecha de creación:              16/03/25
    Fecha última de modificación:   16/03/25
 */

import kotlin.random.Random

fun main() {
    adivinarNumero()
}

fun adivinarNumero(){
    // Número máximo de intentos permitidos
    val nIntentos = 5
    // Generar número aleatorio entre 1 y 30
    val nAdivinar = Random.nextInt(1, 31)

    // Contador de intentos
    var i = 1

    println("Bienvenido a adivina el numero")

    // Bucle de intentos
    while (i <= nIntentos){
        // Solicitar entrada del usuario
        print("Introduce un numero del 1 al 30: ")
        val nIntroducido = readln().toInt()

        // Lógica de comparación
        if (nIntroducido == nAdivinar){
            // Caso de éxito
            println("Felicidades! Has acertado el numero en $i intentos")
            return
        } else if (nIntroducido < nAdivinar){
            // Pista: número mayor
            println("El numero es mayor, te quedan ${nIntentos - i} intentos")
        } else {
            // Pista: número menor
            println("El numero es menor, te quedan ${nIntentos - i} intentos")
        }
        i++
    }
    // Mensaje de fin de juego cuando se agotan los intentos
    println("Has perdido el juego, el numero era $nAdivinar")
}
