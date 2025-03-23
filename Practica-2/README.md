# Práctica 2: Programación Orientada a Objetos Kotlin

## Descripción
Este proyecto contiene cuatro ejercicios prácticos desarrollados en Kotlin, cada uno con una funcionalidad única:
1. CuentaBancaria: Sistema de gestión de cuentas bancarias con validaciones
2. Producto: Sistema de gestión de productos con cálculo de descuentos
3. Figuras Geométricas: Implementación de clases usando herencia y polimorfismo
4. 

## Requisitos
- Kotlin SDK
- Entorno de desarrollo (IntelliJ IDEA recomendado)

## Estructura del Proyecto
- [`Ejercicio 1`](src/practica2-ejercicio1.kt): Implementación de una clase CuentaBancaria
- [`Ejercicio 2`](src/practica2-ejercicio2.kt): Implementación de una clase Producto
- [`Ejercicio 3`](src/practica2-ejercicio3.kt): Implementación de una jerarquía de figuras geométricas
- [`Ejercicio 4`](src/practica2-ejercicio4.kt): 

### Ejercicio 1: Gestión de Cuentas Bancarias
#### Descripción
- Implementación de una clase `CuentaBancaria` que maneja saldo y límite de retiro
- Incluye propiedades con getters y setters validados
- Método para realizar retiros con validaciones de saldo y límite
- Manejo de errores mediante excepciones y mensajes informativos

#### Instrucciones de Ejecución
1. Ejecutar el archivo `practica2-ejercicio1.kt`
2. El programa creará una cuenta de ejemplo con saldo de 1000 y límite de retiro de 500
3. Realizará operaciones de prueba (retiros, depósitos, cambios de límite)
4. Mostrará mensajes informativos sobre el resultado de cada operación

### Ejercicio 2: Gestión de Productos con Descuentos
#### Descripción
- Implementación de una clase `Producto` que maneja precio y descuento aplicable
- Propiedades con getters y setters validados para asegurar valores correctos
- Método para calcular el precio final después de aplicar el descuento
- Función para mostrar información detallada incluyendo precio original, descuento y precio final
- Validaciones para asegurar que el precio sea positivo y el descuento esté entre 0% y 100%

#### Instrucciones de Ejecución
1. Ejecutar el archivo `practica2-ejercicio2.kt`
2. El programa creará un producto de ejemplo con precio de 1000 y descuento del 20%
3. Realizará modificaciones al precio y al porcentaje de descuento
4. Mostrará el cálculo del precio final con el descuento aplicado
5. Incluye ejemplos de manejo de errores para validaciones incorrectas

### Ejercicio 3: Figuras Geométricas con Herencia
#### Descripción
- Implementación de una clase abstracta `Shape` que define la estructura base para todas las figuras
- Tres subclases concretas que heredan de Shape: `Cuadrado`, `Círculo`, y `Rectángulo`
- Uso de propiedades abstractas para área y perímetro
- Implementación de métodos específicos para cada tipo de figura
- Polimorfismo para tratar diferentes figuras de manera uniforme
- Validaciones para asegurar que los parámetros de las figuras sean positivos

#### Instrucciones de Ejecución
1. Ejecutar el archivo `practica2-ejercicio3.kt`
2. El programa creará instancias de diferentes figuras geométricas
3. Calculará el área y perímetro para cada figura
4. Mostrará los resultados de cada cálculo
5. Demostrará el manejo de errores para valores inválidos

### Ejercicio 4: 
#### Descripción
- 
- 
- 

#### Instrucciones de Ejecución
1. 
2. 
3. 
4. 

## Cómo Ejecutar
- En IntelliJ, seleccionar la carpeta `src/` y seleccionar: `Mark Directory as` - `Sources Root`. Y configurar Kotlin con Java.
- Cada ejercicio puede ejecutarse de forma independiente. Seleccione el archivo correspondiente y ejecute el método `main()`.
