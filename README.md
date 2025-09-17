# AppMedica

Una aplicación médica desarrollada en Android con Jetpack Compose que permite a los pacientes agendar citas médicas de manera autónoma, reduciendo la carga telefónica y los errores de scheduling de la clínica usado para practicar y afianzar conocimientos con el uso de Android Studio y Kotlin.

## Índice

- [Características](#características)
  - [Funcionalidades Principales](#funcionalidades-principales)
  - [Características Técnicas](#características-técnicas)
- [Capturas de Pantalla](#capturas-de-pantalla)
- [Arquitectura](#arquitectura)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Requisitos del Sistema](#requisitos-del-sistema)
- [Instalación y Configuración](#instalación-y-configuración)
- [Dependencias Principales](#dependencias-principales)
- [Testing](#testing)
- [Uso de la Aplicación](#uso-de-la-aplicación)
- [Diseño y UX](#diseño-y-ux)
- [Estructura de Archivos](#estructura-de-archivos)
- [Funcionalidades Futuras](#funcionalidades-futuras)
- [Problemas Conocidos](#problemas-conocidos)
- [Métricas del Proyecto](#métricas-del-proyecto)
- [Contacto y Soporte](#contacto-y-soporte)
- [Licencia](#licencia)

## Características

### Funcionalidades Principales
- **Registro de Paciente**: Formulario con validación en tiempo real
- **Selección de Fecha y Hora**: DatePicker y TimePicker integrados
- **Confirmación de Cita**: Resumen profesional con todos los detalles
- **Navegación Fluida**: Implementada con Navigation Compose
- **Validaciones Inteligentes**: Verificación de datos en tiempo real

### Características Técnicas
- **100% Jetpack Compose**: UI moderna y declarativa
- **Material Design 3**: Componentes y themes actualizados
- **Navigation Component**: Navegación entre pantallas
- **State Management**: Gestión eficiente del estado de la aplicación
- **Validación en Tiempo Real**: Feedback inmediato al usuario

## Capturas de Pantalla

### Pantalla 1: Registro del Paciente
- Campo de nombre completo (mínimo 2 palabras)
- Campo de teléfono (exactamente 10 dígitos)
- Botón "Siguiente" habilitado solo con datos válidos
- Mensajes de error informativos
- ![Registro](https://drive.google.com/file/d/1iZOLatYkaaCcOETMURqfWiGhOupcY9Dd/view?usp=sharing)

### Pantalla 2: Selección de Fecha y Hora
- DatePicker que solo permite fechas futuras
- TimePicker con horarios de 8:00 AM a 6:00 PM
- Intervalos de 30 minutos
- Validación de horarios de atención

### Pantalla 3: Confirmación
- Diseño profesional con Material Design
- Ícono de confirmación verde
- Resumen completo de la cita
- Formato legible de fecha y hora en español

## Arquitectura

```
app/
├── src/main/java/com/example/appmedica/
│ └── MainActivity.kt # Actividad principal y toda la lógica
├── res/
│ ├── values/
│ │ ├── colors.xml # Colores del tema
│ │ ├── strings.xml # Textos de la aplicación
│ │ └── themes.xml # Temas Material Design
│ └── AndroidManifest.xml # Configuración de la app
└── build.gradle.kts # Dependencias y configuración
```


## Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación
- **Jetpack Compose** - UI moderna para Android
- **Navigation Compose** - Navegación entre pantallas
- **Material 3** - Design system de Google

### Componentes Principales
- **DatePicker & TimePicker**: Selección de fecha y hora
- **OutlinedTextField**: Campos de entrada con validación
- **Card & Button**: Componentes de interfaz
- **LazyColumn**: Listas eficientes

## Requisitos del Sistema

### Mínimos
- **Android API 24** (Android 7.0) o superior
- **2 GB RAM** mínimo
- **50 MB** espacio de almacenamiento

### Recomendados
- **Android API 33** (Android 13) o superior
- **4 GB RAM** o más
- Conexión a internet para actualizaciones

## Instalación y Configuración

### Prerrequisitos
- Android Studio Hedgehog (2023.1.1) o superior
- JDK 8 o superior
- SDK de Android actualizado

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/yezid-jr/AppMedica.git
   cd AppMedica
   ```

2. **Abrir en Android Studio**
   - File → Open
   - Seleccionar la carpeta del proyecto

3. **Sincronizar dependencias**
   - Esperar que Android Studio sincronice automáticamente
   - Si no, hacer clic en "Sync Now"

4. **Configurar emulador o dispositivo**
   - Crear un AVD (Android Virtual Device)
   - O conectar un dispositivo físico con USB Debugging

5. **Ejecutar la aplicación**
   ```bash
   ./gradlew installDebug
   ```
   O usar el botón "Run" en Android Studio

## Dependencias Principales

```kotlin
dependencies {
    // Jetpack Compose BOM
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    
    // Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")
    
    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.5")
    
    // Activity Compose
    implementation("androidx.activity:activity-compose:1.8.2")
    
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
}
```

## Testing

### Ejecutar pruebas unitarias
```bash
./gradlew test
```

### Ejecutar pruebas de UI
```bash
./gradlew connectedAndroidTest
```

## 📱 Uso de la Aplicación

### Flujo del Usuario

1. **Inicio**: El usuario abre la aplicación
2. **Registro**: Ingresa nombre completo y teléfono
3. **Validación**: El sistema verifica los datos en tiempo real
4. **Fecha/Hora**: Selecciona fecha futura y hora disponible
5. **Confirmación**: Revisa el resumen y confirma la cita
6. **Comprobante**: Recibe confirmación visual de la cita agendada

### Validaciones Implementadas

- **Nombre**: Debe contener al menos nombre y apellido (2 palabras)
- **Teléfono**: Exactamente 10 dígitos numéricos
- **Fecha**: Solo fechas futuras (desde hoy)
- **Hora**: Horario de atención de 8:00 AM a 6:00 PM
- **Intervalos**: Citas cada 30 minutos

## 🎨 Diseño y UX

### Principios de Diseño
- **Simplicidad**: Interfaz limpia y minimalista
- **Accesibilidad**: Textos legibles y botones accesibles
- **Consistencia**: Uso coherente de Material Design
- **Feedback**: Validación inmediata y mensajes claros

### Paleta de Colores
- **Primario**: Azul médico profesional
- **Secundario**: Verde para confirmaciones
- **Error**: Rojo para validaciones
- **Superficie**: Grises neutros

## 📄 Estructura de Archivos

```
MainActivity.kt
├── CitasMedicasApp()           # Composable principal con navegación
├── PantallaRegistro()          # Pantalla de registro del paciente
├── PantallaFechaHora()         # Pantalla de selección de fecha/hora
├── PantallaConfirmacion()      # Pantalla de confirmación final
├── DatePickerDialog()          # Dialog para seleccionar fecha
├── TimePickerDialog()          # Dialog para seleccionar hora
├── InfoRow()                   # Componente para mostrar información
├── PatientData                 # Data class para datos del paciente
├── generateTimeSlots()         # Función para generar horarios
└── formatTime()                # Función para formatear hora
```

## Funcionalidades Futuras

### Próximas Versiones
- [ ] Integración con base de datos local
- [ ] Sincronización con servidor remoto
- [ ] Notificaciones push para recordatorios
- [ ] Historial de citas del paciente
- [ ] Selección de tipo de consulta
- [ ] Integración con calendario del dispositivo
- [ ] Modo oscuro/claro
- [ ] Múltiples idiomas

### Mejoras Técnicas
- [ ] Implementar Repository Pattern
- [ ] Añadir ViewModels
- [ ] Tests automatizados completos
- [ ] CI/CD pipeline
- [ ] Análisis de rendimiento
- [ ] Ofuscación de código

## Problemas Conocidos

### Limitaciones Actuales
- Los datos no se persisten entre sesiones
- Solo funciona offline
- Horarios fijos (no configurables)
- Sin validación de disponibilidad real

### Soluciones Temporales
- Reiniciar la app borra todos los datos
- Usar solo para demostraciones
- Configurar horarios en el código fuente

## Métricas del Proyecto

- **Líneas de código**: ~500
- **Archivos Kotlin**: 1
- **Pantallas**: 3
- **Componentes reutilizables**: 8
- **Validaciones**: 4
- **Tiempo de desarrollo**: 1 día

## 📞 Contacto y Soporte

### Información del Desarrollador
- **Desarrollador**: Yezid-jr
- **Email**: castrogil202@gmail.com
- **GitHub**: [@tu-usuario](https://github.com/yezid-jr)

### Reportar Problemas
- Usar las **Issues** de GitHub para bugs
- Incluir screenshots cuando sea posible
- Describir pasos para reproducir el problema
- Especificar versión de Android y dispositivo

## 📜 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para detalles.

```
MIT License

Copyright (c) 2024 [Tu Nombre]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

**Desarrollado con ❤️ y algunas tacitas de ☕**
