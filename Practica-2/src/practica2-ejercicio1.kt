/**
Descripción del problema:       Se desea implementar una clase CuentaBancaria que maneje saldo y límite de retiro, con métodos set/get validados y operación de retiro.
Autor:                          Leonardo Pachari
Fecha de creación:              23/03/25
Fecha última de modificación:   23/03/25
 */

// Definición de la clase CuentaBancaria con constructor primario
class CuentaBancaria(
    saldoInicial: Number,         // Parámetro para el saldo inicial
    limiteRetiroInicial: Number   // Parámetro para el límite de retiro inicial
) {
    // Variables privadas para almacenar los valores
    private var _saldo: Double
    private var _limiteRetiro: Double

    // Bloque de inicialización para validar y establecer los valores iniciales
    init {
        // Validaciones de los parámetros iniciales
        require(saldoInicial.toDouble() >= 0) { "Error: El saldo inicial no puede ser negativo." }
        require(limiteRetiroInicial.toDouble() > 0) { "Error: El límite de retiro debe ser mayor que cero." }

        // Asignación a las variables privadas
        _saldo = saldoInicial.toDouble()
        _limiteRetiro = limiteRetiroInicial.toDouble()

        // Mensaje de confirmación de creación
        println("Cuenta creada exitosamente con saldo: $_saldo y límite de retiro: $_limiteRetiro\n")
    }

    // Propiedades con getters y setters personalizados
    var saldo: Double
        get() = _saldo   // Getter para obtener el saldo actual
        set(value) {      // Setter para modificar el saldo con validaciones
            when {
                value < 0 -> throw IllegalArgumentException("Error: No se puede establecer un saldo negativo.")
                value == _saldo -> println("Aviso: El nuevo saldo es idéntico al actual: $_saldo\n")
                else -> {
                    _saldo = value
                    println("Operación exitosa: Saldo actualizado a: $_saldo\n")
                }
            }
        }

    var limiteRetiro: Double
        get() = _limiteRetiro   // Getter para obtener el límite de retiro actual
        set(value) {            // Setter para modificar el límite con validaciones
            when {
                value <= 0 -> throw IllegalArgumentException("Error: El límite de retiro debe ser mayor que cero.")
                value == _limiteRetiro -> println("Aviso: El nuevo límite es idéntico al actual: $_limiteRetiro\n")
                else -> {
                    _limiteRetiro = value
                    println("Operación exitosa: Límite de retiro actualizado a: $_limiteRetiro\n")
                }
            }
        }

    // Función para realizar un retiro con validaciones
    fun retirar(monto: Number) {
        val cantidadRetiro = monto.toDouble()

        // Validar que el monto sea positivo
        if (cantidadRetiro <= 0) {
            println("Error: El monto a retirar debe ser mayor que cero.\n")
            return
        }

        // Validaciones para el retiro
        when {
            cantidadRetiro > _saldo -> {
                val faltante = cantidadRetiro - _saldo
                println("Error: Fondos insuficientes. Faltan $faltante para completar esta operación.\n")
            }
            cantidadRetiro > _limiteRetiro -> {
                println("Error: El monto ($cantidadRetiro) excede su límite de retiro actual ($_limiteRetiro).\n")
            }
            else -> {
                _saldo -= cantidadRetiro
                println("Retiro procesado correctamente por $cantidadRetiro. Saldo restante: $_saldo\n")
            }
        }
    }

    // Función para mostrar la información de la cuenta
    fun mostrarInformacion() {
        println("===== INFORMACIÓN DE LA CUENTA =====")
        println("Saldo actual: $_saldo")
        println("Límite de retiro: $_limiteRetiro")
        println("====================================\n")
    }
}

// Función principal para probar la clase
fun main() {
    try {
        // Crear una nueva cuenta bancaria con saldo inicial y límite de retiro
        val miCuenta = CuentaBancaria(1000, 500)

        // Mostrar información inicial
        miCuenta.mostrarInformacion()

        // Probar operaciones
        miCuenta.retirar(200)
        miCuenta.saldo += 300.0

        // Intentar retirar más del límite
        miCuenta.retirar(600)

        // Modificar el límite de retiro
        miCuenta.limiteRetiro = 700.0

        // Intentar el retiro nuevamente
        miCuenta.retirar(600)

        // Mostrar información final
        miCuenta.mostrarInformacion()

    } catch (e: IllegalArgumentException) {
        println("Error en la operación: ${e.message}\n")
    }
}