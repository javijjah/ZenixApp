---
description: El Readme añadido a la página de Github
---

# Zenix App README

Una app de meditación grupal.

## Concepto de la aplicación

Zenix es una app de meditación, la cual nos permite realizar sesiones de meditación grupales sincronizadas. Utiliza el login de Google para mayor comodidad del usuario y no tener que recordar logins

## Elementos de UI

La UI ha sido gestionada a través del componente Relay for Figma, la cual genera componentes prácticamente iguales a los creados en Figma. La app ahorra mucho trabajo a nivel de trabajo en la UI pero hay que saber manejar estos componentes para que se comporten como deseamos, como colocar dentro del Modifier.size() el tamaño exacto que tenemos en Figma o incluir Auto Layout para poder centrarlo. Este componente también incluye RelayColumn y RelayRow para ordenar sus componentes.

## Sobre Realtime Database y su uso en la App

Realtime Database es una alternativa a la base de datos Firestore en Database. Esta aporta un delay mucho menor para sacar la información y una DB mucho más simple, la cual es prácticamente una jerarquía de JSONs. Para utilizarla la activamos desde Firebase y seguimos los pasos que nos indicará el propio Firebase. La Realtime Database desde la consola de Firebase se ve así ![image](https://github.com/javijjah/ZenixApp/assets/120460477/a7000690-192b-4a73-943a-5ce69102852a) Desde nuestra aplicación, guardaremos la generación del ID mayor para hacer la referencia al usuario local y poder crearlo y eliminarlo.

## Criterios de evaluación

### ELEMENTOS QUE DEBE INCLUIR EL DESARROLLO DEL PROYECTO:

Persistencia de datos (local o en la nube)✅

Programación Jetpack Compose.✅

Navegación con NavHost y navcontroller.✅

Arquitectura MVVM (mínimo la lógica separada de la ui con el ViewModel).✅

Organización de clases, objetos e interfaces en paquetes con una lógica CLEAN ARCHITECTURE.✅

Documentación, limpieza y claridad en el código.✅

Algún comentario interno breve si es necesario por ser un código menos legible y más difícil de entender.✅

### ELEMENTOS EXTRA QUE SE VALORAN POSITIVAMENTE PERO NO SON OBLIGATORIOS:

Archivos multimedia (vídeos, audios e imágenes)✅

Implementación de la inyección de dependencias Dagger Hilt.❎

Uso de Casos de uso o interfaces.❎

Uso del elemento Repositorio.❎

Desacoplamiento de capas (transformación de datos que comparten la Vista y el Modelo... veáse en los Ejemplos de Room y Retrofit)❎

Utilización de otros recursos, librerías o componentes que hagan a la aplicación diferencial o aporten nuevos conocimientos gracias a vuestra propia investigación.✅

### Opción pro realizada

**7 pantallas creadas, navegación con NavHost utilizada y dos entidades (Autenticación del usuario/persistencia de los datos de login y usuarios dentro de la sala de meditación)**
