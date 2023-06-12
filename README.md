# MarvelApp

## Introducción

En este proyecto se podrá visualizar una app sobre los personajes de Marvel Comics, en la que muestra una lista detalle sencilla.

![MicrosoftTeams-image (8)](https://github.com/marta1196/MarvelApp/assets/34567942/6a855f6e-d829-40b9-8e80-ad18f2298a97) ![MicrosoftTeams-image (7)](https://github.com/marta1196/MarvelApp/assets/34567942/7b3fd9ab-bf01-40c9-ba4d-28d011f661c7) 

![MicrosoftTeams-image (11)](https://github.com/marta1196/MarvelApp/assets/34567942/fd9c969c-0d13-4a45-8d5a-fed1bbfd6e30) ![MicrosoftTeams-image (9)](https://github.com/marta1196/MarvelApp/assets/34567942/9cfd1d95-ae1a-4672-8251-b675abf59fe4)



## Estructura del proyecto

Este proyecto se compone de 4 capas:

- ui: Es la capa en la que se encarga de la presentación visual e interacción con el usuario. Contiene arquitectura MVVM, vistas clásicas y Jetpack Compose 
- domain: Es la capa que se encarga de centrar los casos de uso y la lógica de negocio de la app para obtener fuente de datos esxternas.
- data: Es la capa de gestionar el acceso a la fuente de datos externas de una base de datos y servicios web
- di: Es la capa de gestionar las dependencias y la configuración de la app.

## Caracteristicas y funcionalidades implementadas
- Lenguaje de programación [Kotlin](https://kotlinlang.org).
- [Corrutinas](https://developer.android.com/kotlin/coroutines?hl=es-419) de kotlin.
- Flujos de kotlin con [Flow](https://developer.android.com/kotlin/flow?hl=es-419).
- Test Unitarios con [JUnit](https://junit.org/junit4/) y [mockk](https://mockk.io).
- Base de datos local con [Room](https://developer.android.com/training/data-storage/room?hl=es-419).
- Acceso a APIs Rest con [Retrofit](https://square.github.io/retrofit/).
- Inyección de dependencias con [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419).
- Paginación con [paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=es-419) en compose.
- [Splash screen](https://developer.android.com/develop/ui/views/launch/splash-screen) al inicializar la app.
- La listas de personajes y comics con [Jetpack Compose](https://developer.android.com/jetpack/compose?hl=es-419).
- El detalle del personaje con vistas clasicas.
