# Práctica 5: Navegación y Comunicación entre Fragments en Android

## Descripción
Este proyecto contiene una aplicación Android que demuestra la navegación entre fragments y la comunicación mediante ViewModel:
- Configurador de Pedidos: Una aplicación que permite a los usuarios configurar un pedido de comida, seleccionando tipo de comida y extras, con flujo de navegación completo.

## Requisitos
- Android Studio Version Meerkat 2024.3.1 
- Dispositivo Android o emulador con API 31 (Android 12) o superior

## Estructura del Proyecto
- [`MainActivity`](ConfiguradorPedidos/app/src/main/java/com/example/configuradorpedidos/MainActivity.kt): Actividad principal que contiene el NavHostFragment
- [`InicioFragment`](ConfiguradorPedidos/app/src/main/java/com/example/configuradorpedidos/InicioFragment.kt): Fragment inicial con botón para comenzar un nuevo pedido
- [`SeleccionComidaFragment`](ConfiguradorPedidos/app/src/main/java/com/example/configuradorpedidos/SeleccionComidaFragment.kt): Permite seleccionar el tipo de comida (pizza, hamburguesa, ensalada)
- [`SeleccionExtrasFragment`](ConfiguradorPedidos/app/src/main/java/com/example/configuradorpedidos/SeleccionExtrasFragment.kt): Permite añadir extras al pedido (bebida, papas fritas, postre)
- [`ResumenPedidoFragment`](ConfiguradorPedidos/app/src/main/java/com/example/configuradorpedidos/ResumenPedidoFragment.kt): Muestra el resumen del pedido y permite confirmar o editar
- [`PedidoViewModel`](ConfiguradorPedidos/app/src/main/java/com/example/configuradorpedidos/viewmodel/PedidoViewModel.kt): ViewModel que mantiene los datos del pedido entre fragments
- [`Pedido`](ConfiguradorPedidos/app/src/main/java/com/example/configuradorpedidos/model/Pedido.kt): Data class que representa el modelo de datos del pedido

## Descripción
- Aplicación que permite al usuario configurar un pedido de comida paso a paso 
- Los datos se comparten entre fragments utilizando un ViewModel compartido 
- El usuario puede navegar hacia adelante y atrás en el flujo 
- Implementa la arquitectura de Navegación de Android Jetpack 
- Permite al usuario editar su pedido desde la pantalla de resumen 
- Maneja el estado de la aplicación durante cambios de configuración (rotación)

## Funcionalidades implementadas
- Navegación entre fragments utilizando Navigation Component 
- Comunicación entre fragments mediante ViewModel 
- Edición de datos con paso de resultados entre fragments 
- Persistencia de datos durante rotación de pantalla 
- Gestión de selecciones con RadioGroup y CheckBoxes 
- Toast para notificaciones al usuario

## Tecnologías y patrones utilizados
- Jetpack Navigation Component para la navegación 
- ViewModel para compartir datos entre fragments 
- LiveData para observar cambios en el modelo 
- ViewBinding para interacción con las vistas 
- Fragments para una interfaz de usuario modular 
- ActivityViewModels para acceso al ViewModel desde múltiples fragments 
- FragmentResult para comunicación entre fragments específicos

## Instrucciones de Configuración
1. Clonar o descargar el repositorio 
2. Abrir Android Studio 
3. Seleccionar "Open an existing Android Studio project"
4. Navegar hasta la carpeta "`Practica-5/ConfiguradorPedidos`" y seleccionarla 
5. Esperar a que Gradle sincronice el proyecto 
6. Asegurarse de tener instaladas las dependencias necesarias (verificar en el archivo `build.gradle`)

## Instrucciones de Ejecución
1. Conectar un dispositivo Android físico mediante USB o iniciar un emulador desde AVD Manager 
2. En Android Studio, seleccionar el dispositivo en el menú desplegable de la barra de herramientas 
3. Hacer clic en el botón "Run" (triángulo verde) o presionar Shift+F10 
4. Esperar a que la aplicación se instale y se inicie en el dispositivo

## Cómo Usar la Aplicación
1. En la pantalla de inicio, presionar el botón "Nuevo Pedido" para comenzar 
2. En la pantalla de selección de comida:
   - Seleccionar una opción (Pizza, Hamburguesa o Ensalada)
   - Presionar "Siguiente" para continuar
3. En la pantalla de selección de extras:
   - Marcar los extras deseados (Bebida, Papas fritas, Postre)
   - Presionar "Siguiente" para continuar
4. En la pantalla de resumen:
   - Revisar los detalles del pedido
   - Seleccionar "Confirmar" para finalizar el pedido, o
   - Seleccionar "Editar" para volver y modificar el pedido 
5. Si se confirma el pedido, la aplicación mostrará un mensaje de confirmación y volverá a la pantalla de inicio 
6. Para probar la persistencia de datos, rotar el dispositivo en cualquier etapa del proceso

## Funcionalidades Adicionales
- Persistencia completa: La aplicación gestiona correctamente la persistencia de datos entre rotaciones y navegación
- Arquitectura MVVM: Implementación de Model-View-ViewModel para una mejor separación de responsabilidades
- Experiencia de usuario mejorada: Flujo de navegación intuitivo con opciones claras en cada paso
- Edición de datos: Posibilidad de editar el pedido antes de confirmar

## Notas Técnicas
- Se utiliza el componente de navegación de Android Jetpack para gestionar la navegación entre fragments
- El ViewModel compartido mantiene los datos del pedido en memoria durante todo el ciclo de vida de la aplicación
- Se implementa setFragmentResultListener para facilitar la comunicación específica entre fragments
- La aplicación sigue los principios de Material Design para una experiencia de usuario coherente
- Se utiliza popBackStack y popUpTo para controlar correctamente el flujo de navegación

## Solución de Problemas
- Si la aplicación no se inicia, verificar la versión mínima de API requerida (API 31)
- Si los cambios no persisten al rotar, verificar que el ViewModel esté correctamente implementado
- Si la navegación no funciona correctamente, revisar el archivo nav_graph.xml para asegurarse de que las acciones están correctamente definidas