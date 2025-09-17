# AppMedica

Una aplicación médica desarrollada en Android con Jetpack Compose.

## Requisitos

- Android Studio Flamingo (2022.2.1) o superior
- JDK 17
- SDK de Android (API 24-34)
- Gradle 8.2+

## Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/TU_USUARIO/appmedica.git
```

2. Abre el proyecto en Android Studio

3. Sincroniza el proyecto con Gradle:
   - `File` → `Sync Project with Gradle Files`

4. Ejecuta la aplicación:
   - Conecta un dispositivo Android o usa un emulador
   - Presiona el botón "Run" o usa `Shift + F10`

## Estructura del Proyecto

```
app/
├── src/main/java/com/example/appmedica/
│   └── MainActivity.kt
├── src/main/res/
└── build.gradle.kts
gradle/
├── libs.versions.toml
└── wrapper/
build.gradle.kts
```

## Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación
- **Jetpack Compose** - UI moderna para Android
- **Navigation Compose** - Navegación entre pantallas
- **Material 3** - Design system de Google

## Contribución

1. Fork el proyecto
2. Crea una branch para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la branch (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request
