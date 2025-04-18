# Práctica 4: Comunicación entre Actividades en Android

## Descripción
Este proyecto contiene 2 aplicaciones Android que demuestran la comunicación entre actividades:
1. Ejercicio 1: Editor de perfil con confirmación
2. Ejercicio 2: Editor de nota rápida

## Requisitos
- Android Studio Version Meerkat 2024.3.1
- Dispositivo Android o emulador con API 24 (Android 7.0) o superior

## Estructura del Proyecto
- [`Ejercicio 1`](Ejercicio1/app/src/main/java/com/example/ejercicio1/MainActivity.kt): Editor de perfil con confirmación
- [`Ejercicio 2`](Ejercicio2/app/src/main/java/com/example/ejercicio2/EditorActivity.kt): Editor de nota rápida

### Ejercicio 1: Editor de perfil con confirmación
#### Descripción
- Aplicación que permite al usuario completar un formulario de perfil personal 
- Los datos se envían a una segunda actividad para revisión y confirmación 
- El usuario puede confirmar los datos o volver a editarlos 
- Implementación de paso de datos entre actividades mediante objetos Parcelable 
- Manejo de resultados con ActivityResultLauncher 
- Manejo del estado de la actividad durante cambios de configuración (rotación)

#### Estructura
- `MainActivity`: Actividad de bienvenida con botón para comenzar 
- `FormularioActivity`: Recoge los datos del usuario 
- `ResumenActivity`: Muestra los datos para confirmación 
- `Usuario`: Data class Parcelable para transferir datos entre actividades

#### Funcionalidades implementadas
- Navegación entre actividades utilizando Intent 
- Transferencia de objetos complejos con Parcelable 
- Devolución de resultados entre actividades 
- Persistencia de datos durante rotación de pantalla 
- Mensajes Toast para notificaciones al usuario

#### Tecnologías y patrones utilizados
- ConstraintLayout y LinearLayout para interfaces responsivas
- Material Design components para entrada de datos
- ActivityResultLauncher para manejo de resultados
- Bundle para guardar/restaurar estado de actividades
- Parcelable para serialización de datos

#### Instrucciones de Ejecución
1. Abrir el proyecto en Android Studio 
2. Ejecutar la aplicación en un emulador o dispositivo físico 
3. Se mostrará la pantalla de bienvenida con un botón para comenzar 
4. Al presionar el botón, se abrirá la actividad de formulario 
5. En la actividad de formulario, se completarán los campos con datos del usuario 
6. Al presionar el botón de confirmación, se abrirá la actividad de resumen 
7. En la actividad de resumen, se mostrarán los datos ingresados por el usuario 
8. El usuario puede editar los datos o volver a la actividad de formulario 

### Ejercicio 2: Editor de nota rápida
#### Descripción
- Aplicación simple para escribir y compartir notas de texto 
- Permite al usuario escribir una nota y enviarla a otra actividad 
- En la segunda actividad, el usuario puede compartir la nota o volver a editarla 
- Implementa comunicación bidireccional entre actividades 
- Preserva el contenido durante cambios de configuración (rotación)

#### Estructura
- `EditorActivity`: Permite al usuario escribir una nota 
- `OpcionesActivity`: Muestra la nota y ofrece opciones para compartir o editar

#### Funcionalidades implementadas
- Campo de texto multlínea para entrada de notas
- Transferencia de texto entre actividades mediante Intent
- Botones para compartir y editar
- Devolución del texto para continuar edición
- Mensajes Toast para simular el compartir por correo

#### Tecnologías y patrones utilizados
- ConstraintLayout para diseño adaptable
- EditText con modo multiline para entrada de texto extenso
- ActivityResultLauncher para manejo avanzado de resultados
- Bundle para salvar/restaurar estado durante rotación
- Intent extras para comunicación entre actividades

#### Instrucciones de Ejecución
1. Abrir el proyecto en Android Studio 
2. Ejecutar la aplicación en un emulador o dispositivo físico 
3. La aplicación mostrará la pantalla de edición de nota 
4. En la actividad de edición, se escribirá una nota 
5. Al presionar el botón de compartir, se abrirá la actividad de opciones 
6. En la actividad de opciones, se mostrarán las opciones de compartir y editar

## Cómo Ejecutar
- Abrir cada carpeta de ejercicio como un proyecto en Android Studio 
- Compilar y ejecutar en un emulador o dispositivo físico 
- Para el Ejercicio 1, completar el formulario y probar el flujo de confirmación 
- Para el Ejercicio 2, escribir una nota y probar las opciones de compartir y editar 
- Probar la rotación del dispositivo para verificar que los datos persisten

## Funcionalidades Adicionales
- Manejo completo del ciclo de vida: Las aplicaciones gestionan correctamente las transiciones entre estados
- Validación de entrada: Campos de entrada con tipos apropiados (número para edad, email para correo)
- Experiencia de usuario mejorada: Diseño intuitivo y feedback visual mediante Toast

## Notas Técnicas
- Se utilizan las últimas prácticas recomendadas para la comunicación entre actividades en Android
- Se implementa onSaveInstanceState() para preservar datos durante cambios de configuración
- ActivityResultLauncher reemplaza el antiguo startActivityForResult() para mayor seguridad y tipado
- Las aplicaciones siguen principios de Material Design para una experiencia de usuario coherente
