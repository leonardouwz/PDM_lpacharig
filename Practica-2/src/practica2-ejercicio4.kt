/**
Descripción del problema:       Se desea desarrollar un sistema de gestión de biblioteca que administre materiales y usuarios, permitiendo registrar, prestar y devolver materiales, así como consultar su disponibilidad y reservas.
Autor:                          Leonardo Pachari
Fecha de creación:              23/03/25
Fecha última de modificación:   23/03/25
 */

import java.util.*

// 1. Clase base abstracta Material
abstract class Material(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int
) {
    // Función abstracta que debe ser implementado por las subclases
    abstract fun mostrarDetalles(): String

    // Identificador único para cada material
    val id: String = UUID.randomUUID().toString().substring(0, 8)

    // Variable para controlar si el material está disponible
    var disponible: Boolean = true
        private set

    // Función para cambiar el estado de disponibilidad
    fun prestar() {
        disponible = false
    }

    fun devolver() {
        disponible = true
    }

    // toString para representación básica
    override fun toString(): String {
        return "$titulo (${if (disponible) "Disponible" else "Prestado"})"
    }
}

// 2. Subclase Libro que hereda de Material
class Libro(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    private val genero: String,
    private val numeroPaginas: Int,
    private val isbn: String
) : Material(titulo, autor, anioPublicacion) {

    // Implementación de la función mostrarDetalles
    override fun mostrarDetalles(): String {
        return """
            |=== DETALLES DEL LIBRO ===
            |ID: $id
            |Título: $titulo
            |Autor: $autor
            |Año de publicación: $anioPublicacion
            |Género: $genero
            |Número de páginas: $numeroPaginas
            |ISBN: $isbn
            |Estado: ${if (disponible) "Disponible" else "Prestado"}
            |=========================
        """.trimMargin()
    }
}

// 3. Subclase Revista que hereda de Material
class Revista(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    private val issn: String,
    private val volumen: Int,
    private val numero: Int,
    private val editorial: String
) : Material(titulo, autor, anioPublicacion) {

    // Implementación de la función mostrarDetalles
    override fun mostrarDetalles(): String {
        return """
            |=== DETALLES DE LA REVISTA ===
            |ID: $id
            |Título: $titulo
            |Autor/Editor: $autor
            |Año de publicación: $anioPublicacion
            |ISSN: $issn
            |Volumen: $volumen
            |Número: $numero
            |Editorial: $editorial
            |Estado: ${if (disponible) "Disponible" else "Prestado"}
            |=============================
        """.trimMargin()
    }
}

// 4. Data class Usuario
data class Usuario(
    val nombre: String,
    val apellido: String,
    val edad: Int
) {
    // Identificador único para cada usuario
    val id: String = UUID.randomUUID().toString().substring(0, 8)

    // Función para mostrar información del usuario
    fun mostrarInformacion(): String {
        return """
            |=== INFORMACIÓN DEL USUARIO ===
            |ID: $id
            |Nombre: $nombre
            |Apellido: $apellido
            |Edad: $edad
            |==============================
        """.trimMargin()
    }

    // toString simplificado
    override fun toString(): String {
        return "$nombre $apellido (ID: $id)"
    }
}

// 5. Interfaz IBiblioteca
interface IBiblioteca {
    // Función para registrar un nuevo material
    fun registrarMaterial(material: Material): Boolean

    // Función para registrar un nuevo usuario
    fun registrarUsuario(usuario: Usuario): Boolean

    // Función para realizar un préstamo
    fun prestarMaterial(idUsuario: String, idMaterial: String): Boolean

    // Función para realizar una devolución
    fun devolverMaterial(idUsuario: String, idMaterial: String): Boolean

    // Función para mostrar materiales disponibles
    fun mostrarMaterialesDisponibles(): List<Material>

    // Función para mostrar materiales reservados por un usuario
    fun mostrarMaterialesPrestadosPorUsuario(idUsuario: String): List<Material>
}

// 6. Clase Biblioteca que implementa IBiblioteca
class Biblioteca : IBiblioteca {
    // Lista de materiales disponibles en la biblioteca
    private val materiales = mutableListOf<Material>()

    // Mapa de usuarios y sus préstamos
    private val prestamos = mutableMapOf<Usuario, MutableList<Material>>()

    // Mapa auxiliar para buscar usuarios por ID
    private val usuariosPorId = mutableMapOf<String, Usuario>()

    // Implementación de registrarMaterial
    override fun registrarMaterial(material: Material): Boolean {
        if (materiales.any { it.id == material.id }) {
            println("Error: Ya existe un material con el ID ${material.id}")
            return false
        }
        materiales.add(material)
        println("Material registrado con éxito: ${material.titulo}")
        return true
    }

    // Implementación de registrarUsuario
    override fun registrarUsuario(usuario: Usuario): Boolean {
        if (usuariosPorId.containsKey(usuario.id)) {
            println("Error: Ya existe un usuario con el ID ${usuario.id}")
            return false
        }
        usuariosPorId[usuario.id] = usuario
        prestamos[usuario] = mutableListOf()
        println("Usuario registrado con éxito: ${usuario.nombre} ${usuario.apellido}")
        return true
    }

    // Implementación de prestarMaterial
    override fun prestarMaterial(idUsuario: String, idMaterial: String): Boolean {
        // Verificar si existe el usuario
        val usuario = usuariosPorId[idUsuario]
        if (usuario == null) {
            println("Error: No existe un usuario con el ID $idUsuario")
            return false
        }

        // Buscar el material
        val material = materiales.find { it.id == idMaterial }
        if (material == null) {
            println("Error: No existe un material con el ID $idMaterial")
            return false
        }

        // Verificar si el material está disponible
        if (!material.disponible) {
            println("Error: El material '${material.titulo}' no está disponible")
            return false
        }

        // Realizar el préstamo
        material.prestar()
        prestamos.getOrPut(usuario) { mutableListOf() }.add(material)
        println("Préstamo realizado con éxito: '${material.titulo}' a ${usuario.nombre} ${usuario.apellido}")
        return true
    }

    // Implementación de devolverMaterial
    override fun devolverMaterial(idUsuario: String, idMaterial: String): Boolean {
        // Verificar si existe el usuario
        val usuario = usuariosPorId[idUsuario]
        if (usuario == null) {
            println("Error: No existe un usuario con el ID $idUsuario")
            return false
        }

        // Verificar si el usuario tiene préstamos
        val materialesUsuario = prestamos[usuario]
        if (materialesUsuario.isNullOrEmpty()) {
            println("Error: El usuario ${usuario.nombre} ${usuario.apellido} no tiene materiales en préstamo")
            return false
        }

        // Buscar el material en los préstamos del usuario
        val material = materialesUsuario.find { it.id == idMaterial }
        if (material == null) {
            println("Error: El usuario no tiene prestado un material con el ID $idMaterial")
            return false
        }

        // Realizar la devolución
        material.devolver()
        materialesUsuario.remove(material)
        println("Devolución realizada con éxito: '${material.titulo}' de ${usuario.nombre} ${usuario.apellido}")
        return true
    }

    // Implementación de mostrarMaterialesDisponibles
    override fun mostrarMaterialesDisponibles(): List<Material> {
        return materiales.filter { it.disponible }
    }

    // Implementación de mostrarMaterialesPrestadosPorUsuario
    override fun mostrarMaterialesPrestadosPorUsuario(idUsuario: String): List<Material> {
        val usuario = usuariosPorId[idUsuario]
        if (usuario == null) {
            println("Error: No existe un usuario con el ID $idUsuario")
            return emptyList()
        }

        return prestamos[usuario] ?: emptyList()
    }

    // Métodos adicionales útiles

    // Obtener todos los usuarios registrados
    fun obtenerUsuarios(): List<Usuario> {
        return usuariosPorId.values.toList()
    }

    // Obtener todos los materiales
    fun obtenerTodosMateriales(): List<Material> {
        return materiales.toList()
    }
}

// FUNCIONES UTILITARIAS REUTILIZABLES
// Función para mostrar una lista de elementos numerados
fun <T> mostrarListaNumerada(items: List<T>, titulo: String, listaVaciaMsg: String, itemFormatter: (T) -> String) {
    println("\n===== $titulo =====")

    if (items.isEmpty()) {
        println(listaVaciaMsg)
        return
    }

    items.forEachIndexed { index, item ->
        println("${index + 1}. ${itemFormatter(item)}")
    }
}

// Función para seleccionar un elemento de una lista
fun <T> seleccionarElemento(scanner: Scanner, items: List<T>, mensajeSeleccion: String): T? {
    if (items.isEmpty()) return null

    print("$mensajeSeleccion: ")
    val indice = try {
        val input = scanner.nextInt() - 1
        scanner.nextLine() // Consumir salto de línea
        input
    } catch (e: InputMismatchException) {
        scanner.nextLine() // Consumir entrada inválida
        println("Entrada inválida. Debe ingresar un número.")
        return null
    }

    return if (indice in items.indices) items[indice] else {
        println("Selección inválida.")
        null
    }
}

// Función para preguntar si se desea ver detalles
fun preguntarVerDetalles(scanner: Scanner): Boolean {
    print("\n¿Desea ver detalles de algún elemento? (S/N): ")
    val respuesta = scanner.nextLine()
    return respuesta.equals("S", ignoreCase = true)
}

// Función para mostrar detalles de un elemento seleccionado
fun <T> mostrarDetallesElemento(scanner: Scanner, items: List<T>, detailsProvider: (T) -> String) {
    if (items.isEmpty()) return

    print("Ingrese el número del elemento: ")
    val indice = try {
        val input = scanner.nextInt() - 1
        scanner.nextLine() // Consumir salto de línea
        input
    } catch (e: InputMismatchException) {
        scanner.nextLine() // Consumir entrada inválida
        println("Entrada inválida. Debe ingresar un número.")
        return
    }

    if (indice in items.indices) {
        println(detailsProvider(items[indice]))
    } else {
        println("Índice inválido.")
    }
}

// Función principal que maneja el menú interactivo
fun main() {
    val biblioteca = Biblioteca()
    val scanner = Scanner(System.`in`)

    // Carga inicial de algunos datos de ejemplo
    cargarDatosIniciales(biblioteca)

    var opcion: Int
    do {
        mostrarMenu()
        try {
            opcion = scanner.nextInt()
            scanner.nextLine() // Consumir el salto de línea

            when (opcion) {
                1 -> registrarNuevoMaterial(scanner, biblioteca)
                2 -> registrarNuevoUsuario(scanner, biblioteca)
                3 -> realizarPrestamo(scanner, biblioteca)
                4 -> realizarDevolucion(scanner, biblioteca)
                5 -> mostrarMaterialesDisponibles(biblioteca, scanner)
                6 -> mostrarMaterialesPrestadosPorUsuario(scanner, biblioteca)
                7 -> mostrarTodosLosMateriales(biblioteca, scanner)
                8 -> mostrarTodosLosUsuarios(biblioteca, scanner)
                0 -> println("\n¡Gracias por usar el Sistema de Gestión de Biblioteca!")
                else -> println("\nOpción inválida. Por favor, intente nuevamente.")
            }
        } catch (e: InputMismatchException) {
            println("\nError: Debe ingresar un número.")
            scanner.nextLine() // Consumir la entrada inválida
            opcion = -1 // Continuar el bucle
        } catch (e: Exception) {
            println("\nError inesperado: ${e.message}")
            opcion = -1 // Continuar el bucle
        }

        if (opcion != 0) {
            println("\nPresione Enter para continuar...")
            scanner.nextLine()
        }

    } while (opcion != 0)
}

// Función para mostrar el menú principal
fun mostrarMenu() {
    println("\n===== SISTEMA DE GESTIÓN DE BIBLIOTECA =====")
    println("1. Registrar nuevo material")
    println("2. Registrar nuevo usuario")
    println("3. Realizar préstamo")
    println("4. Realizar devolución")
    println("5. Mostrar materiales disponibles")
    println("6. Mostrar materiales prestados por usuario")
    println("7. Mostrar todos los materiales")
    println("8. Mostrar todos los usuarios")
    println("0. Salir")
    print("\nSeleccione una opción: ")
}

// Función para cargar datos iniciales de ejemplo
fun cargarDatosIniciales(biblioteca: Biblioteca) {
    // Crear algunos libros
    val libro1 = Libro(
        "Cien años de soledad",
        "Gabriel García Márquez",
        1967,
        "Realismo mágico",
        471,
        "978-0307474728"
    )
    val libro2 = Libro(
        "El señor de los anillos",
        "J.R.R. Tolkien",
        1954,
        "Fantasía",
        1178,
        "978-0618640157"
    )

    // Crear algunas revistas
    val revista1 = Revista(
        "National Geographic",
        "National Geographic Society",
        2023,
        "0027-9358",
        243,
        5,
        "National Geographic Partners"
    )
    val revista2 = Revista(
        "Science",
        "American Association for the Advancement of Science",
        2024,
        "0036-8075",
        383,
        6631,
        "AAAS"
    )

    // Registrar materiales
    biblioteca.registrarMaterial(libro1)
    biblioteca.registrarMaterial(libro2)
    biblioteca.registrarMaterial(revista1)
    biblioteca.registrarMaterial(revista2)

    // Crear algunos usuarios
    val usuario1 = Usuario("Juan", "Pérez", 30)
    val usuario2 = Usuario("María", "González", 25)

    // Registrar usuarios
    biblioteca.registrarUsuario(usuario1)
    biblioteca.registrarUsuario(usuario2)

    // Realizar algunos préstamos
    biblioteca.prestarMaterial(usuario1.id, libro1.id)
}

// Función para registrar un nuevo material
fun registrarNuevoMaterial(scanner: Scanner, biblioteca: Biblioteca) {
    println("\n===== REGISTRAR NUEVO MATERIAL =====")
    println("¿Qué tipo de material desea registrar?")
    println("1. Libro")
    println("2. Revista")
    print("Seleccione una opción: ")

    val opcion = scanner.nextInt()
    scanner.nextLine() // Consumir el salto de línea

    print("Título: ")
    val titulo = scanner.nextLine()

    print("Autor/Editor: ")
    val autor = scanner.nextLine()

    print("Año de publicación: ")
    val anioPublicacion = scanner.nextInt()
    scanner.nextLine() // Consumir el salto de línea

    when (opcion) {
        1 -> {
            print("Género: ")
            val genero = scanner.nextLine()

            print("Número de páginas: ")
            val numeroPaginas = scanner.nextInt()
            scanner.nextLine() // Consumir el salto de línea

            print("ISBN: ")
            val isbn = scanner.nextLine()

            val libro = Libro(titulo, autor, anioPublicacion, genero, numeroPaginas, isbn)
            biblioteca.registrarMaterial(libro)
        }
        2 -> {
            print("ISSN: ")
            val issn = scanner.nextLine()

            print("Volumen: ")
            val volumen = scanner.nextInt()

            print("Número: ")
            val numero = scanner.nextInt()
            scanner.nextLine() // Consumir el salto de línea

            print("Editorial: ")
            val editorial = scanner.nextLine()

            val revista = Revista(titulo, autor, anioPublicacion, issn, volumen, numero, editorial)
            biblioteca.registrarMaterial(revista)
        }
        else -> println("Opción inválida.")
    }
}

// Función para registrar un nuevo usuario
fun registrarNuevoUsuario(scanner: Scanner, biblioteca: Biblioteca) {
    println("\n===== REGISTRAR NUEVO USUARIO =====")

    print("Nombre: ")
    val nombre = scanner.nextLine()

    print("Apellido: ")
    val apellido = scanner.nextLine()

    print("Edad: ")
    val edad = scanner.nextInt()
    scanner.nextLine() // Consumir el salto de línea

    val usuario = Usuario(nombre, apellido, edad)
    biblioteca.registrarUsuario(usuario)
}

// Función para realizar un préstamo
fun realizarPrestamo(scanner: Scanner, biblioteca: Biblioteca) {
    println("\n===== REALIZAR PRÉSTAMO =====")

    // Mostrar y seleccionar usuarios
    val usuarios = biblioteca.obtenerUsuarios()
    if (usuarios.isEmpty()) {
        println("No hay usuarios registrados.")
        return
    }

    mostrarListaNumerada(usuarios, "Usuarios disponibles", "No hay usuarios registrados.") { it.toString() }
    val usuario = seleccionarElemento(scanner, usuarios, "Seleccione un usuario (número)") ?: return

    // Mostrar y seleccionar materiales disponibles
    val materialesDisponibles = biblioteca.mostrarMaterialesDisponibles()
    if (materialesDisponibles.isEmpty()) {
        println("No hay materiales disponibles para préstamo.")
        return
    }

    mostrarListaNumerada(materialesDisponibles, "Materiales disponibles", "No hay materiales disponibles.") { it.toString() }
    val material = seleccionarElemento(scanner, materialesDisponibles, "Seleccione un material (número)") ?: return

    biblioteca.prestarMaterial(usuario.id, material.id)
}

// Función para realizar una devolución
fun realizarDevolucion(scanner: Scanner, biblioteca: Biblioteca) {
    println("\n===== REALIZAR DEVOLUCIÓN =====")

    // Mostrar y seleccionar usuarios
    val usuarios = biblioteca.obtenerUsuarios()
    if (usuarios.isEmpty()) {
        println("No hay usuarios registrados.")
        return
    }

    mostrarListaNumerada(usuarios, "Usuarios", "No hay usuarios registrados.") { it.toString() }
    val usuario = seleccionarElemento(scanner, usuarios, "Seleccione un usuario (número)") ?: return

    // Mostrar y seleccionar materiales prestados al usuario
    val materialesPrestados = biblioteca.mostrarMaterialesPrestadosPorUsuario(usuario.id)
    if (materialesPrestados.isEmpty()) {
        println("El usuario no tiene materiales en préstamo.")
        return
    }

    mostrarListaNumerada(
        materialesPrestados,
        "Materiales prestados a ${usuario.nombre} ${usuario.apellido}",
        "El usuario no tiene materiales en préstamo."
    ) { it.titulo }

    val material = seleccionarElemento(
        scanner,
        materialesPrestados,
        "Seleccione un material para devolver (número)"
    ) ?: return

    biblioteca.devolverMaterial(usuario.id, material.id)
}

// Función para mostrar materiales disponibles
fun mostrarMaterialesDisponibles(biblioteca: Biblioteca, scanner: Scanner) {
    val materialesDisponibles = biblioteca.mostrarMaterialesDisponibles()

    mostrarListaNumerada(
        materialesDisponibles,
        "MATERIALES DISPONIBLES",
        "No hay materiales disponibles."
    ) { it.titulo }

    if (materialesDisponibles.isNotEmpty() && preguntarVerDetalles(scanner)) {
        mostrarDetallesElemento(scanner, materialesDisponibles) { it.mostrarDetalles() }
    }
}

// Función para mostrar materiales prestados por usuario
fun mostrarMaterialesPrestadosPorUsuario(scanner: Scanner, biblioteca: Biblioteca) {
    // Mostrar y seleccionar usuarios
    val usuarios = biblioteca.obtenerUsuarios()

    mostrarListaNumerada(usuarios, "MATERIALES PRESTADOS POR USUARIO", "No hay usuarios registrados.") { "${it.nombre} ${it.apellido}" }
    val usuario = seleccionarElemento(scanner, usuarios, "Seleccione un usuario (número)") ?: return

    // Mostrar materiales prestados al usuario
    val materialesPrestados = biblioteca.mostrarMaterialesPrestadosPorUsuario(usuario.id)

    mostrarListaNumerada(
        materialesPrestados,
        "Materiales prestados a ${usuario.nombre} ${usuario.apellido}",
        "El usuario no tiene materiales en préstamo."
    ) { it.titulo }

    if (materialesPrestados.isNotEmpty() && preguntarVerDetalles(scanner)) {
        mostrarDetallesElemento(scanner, materialesPrestados) { it.mostrarDetalles() }
    }
}

// Función para mostrar todos los materiales
fun mostrarTodosLosMateriales(biblioteca: Biblioteca, scanner: Scanner) {
    val materiales = biblioteca.obtenerTodosMateriales()

    mostrarListaNumerada(materiales, "TODOS LOS MATERIALES", "No hay materiales registrados.") { it.toString() }

    if (materiales.isNotEmpty() && preguntarVerDetalles(scanner)) {
        mostrarDetallesElemento(scanner, materiales) { it.mostrarDetalles() }
    }
}

// Función para mostrar todos los usuarios
fun mostrarTodosLosUsuarios(biblioteca: Biblioteca, scanner: Scanner) {
    val usuarios = biblioteca.obtenerUsuarios()

    mostrarListaNumerada(usuarios, "TODOS LOS USUARIOS", "No hay usuarios registrados.") { "${it.nombre} ${it.apellido}" }

    if (usuarios.isNotEmpty() && preguntarVerDetalles(scanner)) {
        // Seleccionar y mostrar detalles del usuario
        val indice = try {
            print("Ingrese el número del usuario: ")
            val input = scanner.nextInt() - 1
            scanner.nextLine() // Consumir salto de línea
            input
        } catch (e: InputMismatchException) {
            scanner.nextLine() // Consumir entrada inválida
            println("Entrada inválida. Debe ingresar un número.")
            return
        }

        if (indice in usuarios.indices) {
            val usuario = usuarios[indice]
            println(usuario.mostrarInformacion())

            // Mostrar materiales prestados
            val materialesPrestados = biblioteca.mostrarMaterialesPrestadosPorUsuario(usuario.id)
            if (materialesPrestados.isNotEmpty()) {
                println("Materiales en préstamo:")
                materialesPrestados.forEachIndexed { i, material ->
                    println("${i + 1}. ${material.titulo}")
                }
            } else {
                println("El usuario no tiene materiales en préstamo.")
            }
        } else {
            println("Índice inválido.")
        }
    }
}