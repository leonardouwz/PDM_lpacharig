/**
    Descripción del problema:       Se desea una calculadora básica funcional con las operaciones +, -, *, / mediante un menú
    Autor:                          Leonardo Pachari
    Fecha de creación:              16/03/25
    Fecha última de modificación:   16/03/25
 */

/*
 * Función principal que inicia la calculadora
 * Solicita un valor inicial y llama a la función calculadora
 */
fun main() {
    print("Ingrese el valor inicial: ")
    val num = readln().toDouble()
    calculadora(num)
}

fun mostrarMenu(){
    // Mostrar menú de opciones
    println("Bienvenido a la calculadora")
    println("==== Menú ====")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    println("5. Salir")
    print("Elige una opción: ")
}

fun calculadora(num: Double){
    mostrarMenu()
    val opcion = readln().toInt()

    // Mapeo de opciones a operaciones para mejorar legibilidad
    val operaciones = mapOf(1 to "sumar", 2 to "restar", 3 to "multiplicar", 4 to "dividir")

    // Salir del programa si la opción es mayor a 4
    if (opcion > 4) {
        println("Hasta luego")
        return
    } else {
        // Solicitar el segundo valor para la operación
        print("Ingresa el valor que va a ${operaciones[opcion]} a $num: ")
        val valor = readln().toDouble()

        // Realizar la operación según la opción seleccionada
        val resultado = when (opcion) {
            1 -> num + valor    // Suma
            2 -> num - valor    //Resta
            3 -> num * valor    //Multiplicación
            4 -> num / valor    // División
            else -> 0.0
        }
        // Mostrar resultado y llamar recursivamente para continuar calculando
        println("El resultado de la operación es: $resultado")
        calculadora(resultado)
    }
}