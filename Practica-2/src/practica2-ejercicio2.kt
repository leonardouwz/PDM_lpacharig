/**
Descripción del problema:       Se desea implementar una clase Producto que maneje precio y descuento aplicable, con métodos set/get validados y operación para calcular el precio final con descuentos.
Autor:                          Leonardo Pachari
Fecha de creación:              23/03/25
Fecha última de modificación:   23/03/25
 */

// Definición de la clase Producto con constructor primario
class Producto(
    precioInicial: Number,      // Parámetro para el precio inicial
    descuentoInicial: Number    // Parámetro para el descuento inicial
) {
    // Variables privadas para almacenar los valores
    private var _precio: Double
    private var _descuento: Double

    // Bloque de inicialización para validar y establecer los valores iniciales
    init {
        // Validaciones de los parámetros iniciales
        require(precioInicial.toDouble() > 0) { "Error: El precio del producto debe ser mayor que cero." }
        require(descuentoInicial.toDouble() in 0.0..100.0) { "Error: El descuento debe estar entre 0 y 100." }

        // Asignación a las variables privadas
        _precio = precioInicial.toDouble()
        _descuento = descuentoInicial.toDouble()

        // Mensaje de confirmación de creación
        println("Producto creado exitosamente con precio: $_precio y descuento: $_descuento%\n")
    }

    // Propiedades con getters y setters personalizados
    var precio: Double
        get() = _precio   // Getter para obtener el precio actual
        set(value) {      // Setter para modificar el precio con validaciones
            when {
                value <= 0 -> throw IllegalArgumentException("Error: El precio debe ser mayor que cero.")
                value == _precio -> println("Aviso: El nuevo precio es idéntico al actual: $_precio\n")
                else -> {
                    _precio = value
                    println("Operación exitosa: Precio actualizado a: $_precio\n")
                }
            }
        }

    var descuento: Double
        get() = _descuento   // Getter para obtener el descuento actual
        set(value) {         // Setter para modificar el descuento con validaciones
            when {
                value < 0 || value > 100 -> throw IllegalArgumentException("Error: El descuento debe estar entre 0 y 100.")
                value == _descuento -> println("Aviso: El nuevo descuento es idéntico al actual: $_descuento%\n")
                else -> {
                    _descuento = value
                    println("Operación exitosa: Descuento actualizado a: $_descuento%\n")
                }
            }
        }

    // Función para calcular el precio final después de aplicar el descuento
    fun calcularPrecioFinal(): Double {
        // Calcular el monto del descuento
        val montoDescuento = _precio * (_descuento / 100)
        // Calcular el precio final restando el descuento
        val precioFinal = _precio - montoDescuento

        return precioFinal
    }

    // Función para mostrar la información detallada del producto
    fun mostrarInformacion() {
        val precioFinal = calcularPrecioFinal()
        val montoDescuento = _precio - precioFinal

        println("===== INFORMACIÓN DEL PRODUCTO =====")
        println("Precio original: $_precio")
        println("Descuento aplicado: $_descuento%")
        println("Monto del descuento: $montoDescuento")
        println("Precio final: $precioFinal")
        println("====================================\n")
    }
}

// Función principal para probar la clase
fun main() {
    // Crear un nuevo producto con precio inicial y descuento
    val producto1 = Producto(1000, 20)

    // Mostrar información inicial
    producto1.mostrarInformacion()

    // Modificar el precio
    producto1.precio = 1200.0

    // Modificar el descuento
    producto1.descuento = 15.0

    // Mostrar información actualizada
    producto1.mostrarInformacion()

    // Probar validaciones con valores incorrectos
    try {
        producto1.descuento = 110.0 // Descuento fuera de rango
    } catch (e: IllegalArgumentException) {
        println("${e.message}\n")
    }

    try {
        producto1.precio = -50.0 // Precio negativo
    } catch (e: IllegalArgumentException) {
        println("${e.message}\n")
    }

    // Crear otro producto para comparar
    val producto2 = Producto(500, 10)
    println("Información del otro producto: ")
    producto2.mostrarInformacion()
}