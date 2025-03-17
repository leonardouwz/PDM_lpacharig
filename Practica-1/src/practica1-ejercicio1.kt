/**
    Descripción del problema:       Se desea saber el dinero recibido de un empleado teniendo de base el salario y la evaluación obtenida
    Autor:                          Leonardo Pachari
    Fecha de creación:              15/03/25
    Fecha última de modificación:   16/03/25
 */

fun calcularSueldo(salario: Double , evaluacion: Double ){
    // Calcula dinero adicional proporcional a la evaluación
    val dinero: Double = salario * (evaluacion / 10)

    // Determina el nivel de rendimiento según la puntuación
    val rendimiento = when(evaluacion) {
        in 0.0..3.0 -> "Inaceptable"
        in 4.0..6.0 -> "Aceptable"
        in 7.0..10.0 -> "Meritorio"
        else -> "Nivel no válido"
    }

    // Muestra resultados de la evaluación
    println("Resultados de la Evaluación:")
    println("Nivel de Rendimiento: $rendimiento")
    println("Cantidad de Dinero Recibido $$dinero")
}

fun main() {
    print("Ingrese su salario: ")
    val salario = readln().toDouble()
    print("Ingrese su evaluación (0-10): ")
    val evaluacion = readln().toDouble()

    calcularSueldo(salario, evaluacion)
}