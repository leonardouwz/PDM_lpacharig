/**
Descripción del problema:       Se desea implementar una clase Producto que maneje precio y descuento aplicable, con métodos set/get validados y operación para calcular el precio final con descuentos.
Autor:                          Leonardo Pachari
Fecha de creación:              23/03/25
Fecha última de modificación:   23/03/25
 */

// Importación del número PI para mejor precisión
import kotlin.math.PI

// Clase abstracta Shape que define la estructura para todas las figuras
abstract class Shape {
    // Propiedades abstractas para área y perímetro
    abstract val area: Double
    abstract val perimetro: Double

    // Función para calcular el área - debe ser implementado por las subclases
    abstract fun calcularArea(): Double

    // Función para calcular el perímetro - debe ser implementado por las subclases
    abstract fun calcularPerimetro(): Double

    // Función para imprimir los resultados de área y perímetro
    fun imprimirResultados() {
        println("===== Resultados de la figura ${this.javaClass.simpleName} =====")
        println("Área: $area")
        println("Perímetro: $perimetro")
        println("==========================================\n")
    }
}

// Subclase Cuadrado que hereda de Shape
class Cuadrado : Shape {
    // Propiedad para almacenar el lado del cuadrado
    private var lado: Double = 0.0

    // Implementación de las propiedades abstractas
    override val area: Double
        get() = calcularArea()

    override val perimetro: Double
        get() = calcularPerimetro()

    // Constructor secundario que recibe el valor del lado
    constructor(lado: Double) {
        require(lado > 0) { "El lado del cuadrado debe ser mayor que cero." }
        this.lado = lado
        println("Cuadrado creado con lado: $lado")
    }

    // Implementación del cálculo del área
    override fun calcularArea(): Double {
        return lado * lado
    }

    // Implementación del cálculo del perímetro
    override fun calcularPerimetro(): Double {
        return 4 * lado
    }
}

// Subclase Círculo que hereda de Shape
class Circulo : Shape {
    // Propiedad para almacenar el radio del círculo
    private var radio: Double = 0.0

    // Implementación de las propiedades abstractas
    override val area: Double
        get() = calcularArea()

    override val perimetro: Double
        get() = calcularPerimetro()

    // Constructor secundario que recibe el valor del radio
    constructor(radio: Double) {
        require(radio > 0) { "El radio del círculo debe ser mayor que cero." }
        this.radio = radio
        println("Círculo creado con radio: $radio")
    }

    // Implementación del cálculo del área
    override fun calcularArea(): Double {
        return PI * radio * radio
    }

    // Implementación del cálculo del perímetro (circunferencia)
    override fun calcularPerimetro(): Double {
        return 2 * PI * radio
    }
}

// Subclase Rectángulo que hereda de Shape
class Rectangulo : Shape {
    // Propiedades para almacenar el largo y ancho del rectángulo
    private var largo: Double = 0.0
    private var ancho: Double = 0.0

    // Implementación de las propiedades abstractas
    override val area: Double
        get() = calcularArea()

    override val perimetro: Double
        get() = calcularPerimetro()

    // Constructor secundario que recibe los valores de largo y ancho
    constructor(largo: Double, ancho: Double) {
        require(largo > 0) { "El largo del rectángulo debe ser mayor que cero." }
        require(ancho > 0) { "El ancho del rectángulo debe ser mayor que cero." }
        this.largo = largo
        this.ancho = ancho
        println("Rectángulo creado con largo: $largo y ancho: $ancho")
    }

    // Implementación del cálculo del área
    override fun calcularArea(): Double {
        return largo * ancho
    }

    // Implementación del cálculo del perímetro
    override fun calcularPerimetro(): Double {
        return 2 * (largo + ancho)
    }
}

// Función principal para probar las clases
fun main() {
    // Lista para almacenar todas las figuras
    val figuras = mutableListOf<Shape>()

    // Crear instancias de las diferentes figuras
    println("\n==== Creando figuras ====")
    val cuadrado = Cuadrado(5.0)
    val circulo = Circulo(3.0)
    val rectangulo = Rectangulo(6.0, 4.0)

    // Agregar figuras a la lista
    figuras.add(cuadrado)
    figuras.add(circulo)
    figuras.add(rectangulo)

    // Calcular e imprimir área y perímetro para cada figura
    println("\n==== Resultados de las operaciones ====")
    for (figura in figuras) {
        figura.calcularArea()
        figura.calcularPerimetro()
        figura.imprimirResultados()
    }

    // Crear una nueva figura con valores diferentes
    val cuadradoGrande = Cuadrado(10.0)
    cuadradoGrande.imprimirResultados()

    // Intentar crear una figura con valores inválidos
    try {
        val circuloInvalido = Circulo(-2.0)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}\n")
    }
}