# Práctica 3: Aplicaciones Android

## Descripción
Este proyecto contiene 2 aplicaciones android:
1. Ejercicio 1: Aplicación con interacción de imagen 
2. Ejercicio 2: Reproductor de música básico

## Requisitos
- Android Studio Version Meerkat 2024.3.1
- Dispositivo Android o emulador

## Estructura del Proyecto
- [`Ejercicio 1`](Ejercicio1/app/src/main/java/com/example/practica3_ejercicio1/MainActivity.kt): Aplicación de imagen interactiva
- [`Ejercicio 2`](Ejercicio2/app/src/main/java/com/example/practica3_ejercicio2/MainActivity.kt): Reproductor de música básico

### Ejercicio 1: Aplicación de Imagen Interactiva
#### Descripción
- Implementación de una aplicación Android básica que muestra una imagen al usuario 
- La aplicación permite al usuario hacer clic en la imagen, mostrando un mensaje Toast
- Diseño simple pero efectivo con una imagen central
- Implementación de listener para capturar eventos de clic
- Utilización de ConstraintLayout para el diseño de la interfaz

#### Estructura
- `MainActivity.kt`: Contiene la lógica para manejar el clic en la imagen
- `activity_main.xml`: Define el layout con un ImageView central
- `AndroidManifest.xml`: Configuración básica de la aplicación

#### Instrucciones de Ejecución
1. Abrir el proyecto en Android Studio 
2. Ejecutar la aplicación en un emulador o dispositivo físico 
3. Se mostrará una imagen en el centro de la pantalla 
4. Al hacer clic en la imagen, aparecerá un mensaje Toast: "¡Has hecho clic en la imagen!"

### Ejercicio 2: Reproductor de Música Básico
#### Descripción
- Implementación de un reproductor de música con funcionalidades básicas 
- Interfaz de usuario con controles de reproducción (reproducir, pausar, detener)
- Navegación entre canciones (anterior, siguiente)
- Visualización de metadatos de la canción (título, artista)
- Mostrador de portada del álbum 
- Barra de progreso (SeekBar) con tiempos actual y total 
- Indicador de estado de reproducción

#### Características
- Reproducción de canciones desde recursos raw
- Extracción de metadatos (título, artista, álbum) de archivos de audio
- Manejo del ciclo de vida de MediaPlayer
- Actualización en tiempo real de la barra de progreso
- Gestión de cambio de canciones con actualización de UI
- Manejo adecuado de recursos para evitar fugas de memoria

#### Estructura
- `MainActivity.kt`: Contiene toda la lógica del reproductor de música
- `activity_main.xml`: Define el layout completo con controles e información
- `raw/`: Directorio con archivos de audio para reproducción
- `drawable/`: Directorio con imágenes para portadas de álbumes

#### Instrucciones de Ejecución
1. Abrir el proyecto en Android Studio 
2. Ejecutar la aplicación en un emulador o dispositivo físico 
3. La aplicación mostrará la interfaz del reproductor con la canción inicial cargada 
4. Usar los botones para controlar la reproducción y navegar entre canciones
5. Observar cómo se actualizan los metadatos y la portada al cambiar de canción 
6. Utilizar la barra de progreso para saltar a diferentes partes de la canción

## Cómo Ejecutar
- Abrir cada carpeta de ejercicio como un proyecto en Android Studio 
- Asegurarse de tener instalado el SDK de Android correspondiente 
- Configurar un emulador o conectar un dispositivo físico para pruebas 
- Ejecutar la aplicación presionando el botón Run (▶)
