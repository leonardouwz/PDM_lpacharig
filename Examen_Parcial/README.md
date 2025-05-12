# Exámen Parcial: Juego de Colores

## Descripción
Esta aplicación Android implementa un juego de reflejos basado en colores, donde el usuario debe identificar y pulsar el botón que corresponde al color mostrado en pantalla dentro de un tiempo límite. El objetivo es conseguir la mayor puntuación posible en 30 segundos.

## Requisitos
- Android Studio Version Meerkat 2024.3.1 
- Dispositivo Android o emulador con API 31 (Android 12) o superior
- Gradle 8.10.0 o superior

## Estructura del Proyecto
- [`MainActivity`](JuegodeColores/app/src/main/java/com/example/juegodecolores/MainActivity.kt): Actividad principal que contiene el NavHostFragment
- [`WelcomeFragment`](JuegodeColores/app/src/main/java/com/example/juegodecolores/WelcomeFragment.kt): Fragment inicial con pantalla de bienvenida e instrucciones
- [`GameFragment`](JuegodeColores/app/src/main/java/com/example/juegodecolores/GameFragment.kt): Fragment principal del juego donde se muestran los colores y botones
- [`ResultFragment`](JuegodeColores/app/src/main/java/com/example/juegodecolores/ResultFragment.kt): Fragment de resultados que muestra la puntuación final
- [`ColorManager`](JuegodeColores/app/src/main/java/com/example/juegodecolores/ColorManager.kt): Clase que gestiona los colores del juego
- [`ScoreManager`](JuegodeColores/app/src/main/java/com/example/juegodecolores/ScoreManager.kt): Clase que gestiona las puntuaciones usando SharedPreferences

## Descripción Detallada
- Juego dinámico que pone a prueba los reflejos y la capacidad de identificación de colores 
- Pantalla de bienvenida con instrucciones claras para el usuario 
- Temporizador de 30 segundos para completar la mayor cantidad de aciertos 
- Sistema de puntuación que registra la mejor marca histórica 
- Pantalla de resultados personalizada según el rendimiento 
- Animaciones para mejorar la experiencia de usuario 
- Navegación fluida entre las diferentes pantallas

## Funcionalidades implementadas
- Navegación entre fragments utilizando Navigation Component 
- Persistencia de datos con SharedPreferences para guardar la mejor puntuación 
- Temporizador con CountDownTimer para controlar la duración del juego 
- Generación aleatoria de colores 
- Verificación de respuestas correctas e incremento de puntuación 
- Animaciones para cambios de color, pulsación de botones y transiciones 
- Gestión personalizada de colores con ColorManager 
- Mensajes adaptados según la puntuación obtenida

## Tecnologías y patrones utilizados
- Jetpack Navigation Component para la navegación 
- ViewBinding para interacción con las vistas 
- Fragments para una interfaz de usuario modular 
- SharedPreferences para almacenamiento persistente de puntuaciones 
- Material Design para la interfaz de usuario 
- Data Binding para vinculación de datos 
- Animaciones XML para efectos visuales 
- Safe Args para el paso seguro de argumentos entre fragments

## Instrucciones de Configuración
1. Clonar o descargar el repositorio 
2. Abrir Android Studio 
3. Seleccionar "Open an existing Android Studio project"
4. Navegar hasta la carpeta `Examen_Parcial/JuegodeColores` y seleccionarla 
5. Esperar a que Gradle sincronice el proyecto 
6. Asegurarse de tener instaladas las dependencias necesarias (verificar en el archivo `build.gradle.kts`)

## Instrucciones de Ejecución
1. Conectar un dispositivo Android físico mediante USB o iniciar un emulador desde AVD Manager 
2. En Android Studio, seleccionar el dispositivo en el menú desplegable de la barra de herramientas 
3. Hacer clic en el botón "Run" (triángulo verde) o presionar Shift+F10 
4. Esperar a que la aplicación se instale y se inicie en el dispositivo

## Cómo Usar la Aplicación
1. En la pantalla de bienvenida, leer las instrucciones y pulsar "Iniciar Juego"
2. En la pantalla del juego:
   - Observar el color que aparece en el cuadro central 
   - Pulsar el botón con el nombre correspondiente a ese color 
   - Cada acierto suma 1 punto 
   - Dispone de 30 segundos para conseguir la mayor puntuación posible 
3. Al finalizar el tiempo, se mostrará la pantalla de resultados con:
   - Tu puntuación final 
   - Mensaje personalizado según tu rendimiento 
   - Si has conseguido un nuevo récord, se mostrará un mensaje especial 
   - Opciones para "Jugar de nuevo" o "Volver al menú"

## Funcionalidades Adicionales
- Sistema de récords persistente: La aplicación guarda la mejor puntuación histórica 
- Animaciones visuales: Efectos de pulsación en botones y transiciones entre pantallas 
- Mensajes motivacionales: Diferentes mensajes según la puntuación obtenida 
- Diseño responsive: Funciona correctamente en diferentes tamaños de pantalla 
- Material Design: Implementación de componentes visuales modernos

## Notas Técnicas
- Se utiliza el componente de navegación de Android Jetpack para gestionar la navegación entre fragments 
- Las animaciones están definidas en archivos XML para mejorar rendimiento y mantenibilidad 
- La clase ColorManager centraliza la gestión de colores para facilitar el mantenimiento 
- ScoreManager implementa la persistencia de datos utilizando SharedPreferences 
- Se utilizan CardView y constraintLayout para un diseño moderno y adaptable

## Solución de Problemas
- Si la aplicación no se inicia, verificar la versión mínima de API requerida (API 31)
- Si las animaciones no funcionan correctamente, asegurarse de que la aceleración por hardware está activada
- Si no se guardan las puntuaciones, verificar los permisos de almacenamiento de la aplicación