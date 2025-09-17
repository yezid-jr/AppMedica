package com.example.appmedica
//Todas las importaciones necesarias
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*


//clase principal de la app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CitasMedicasApp()
            }
        }
    }
}

//funcion principal de la app
@Composable
fun CitasMedicasApp() {
    val navController = rememberNavController()
    val patientData = remember { mutableStateOf(PatientData()) }

    //se utiliza para navegar entre pantallas
    NavHost(
        navController = navController,
        startDestination = "registro" //pantalla inicial
    ) {
        composable("registro") { //pantalla de registro
            PantallaRegistro(
                navController = navController,
                patientData = patientData.value
            ) { newData ->
                patientData.value = newData //actualiza los datos del paciente
            }
        }
        composable("fecha_hora") { //pantalla de fecha y hora
            PantallaFechaHora(
                navController = navController,
                patientData = patientData.value
            ) { newData ->
                patientData.value = newData //actualiza los datos del paciente
            }
        }
        composable("confirmacion") { //pantalla de confirmacion
            PantallaConfirmacion(patientData = patientData.value) //muestra los datos del paciente
        }
    }
}

//clase de datos del paciente
data class PatientData(
    val nombre: String = "",
    val telefono: String = "",
    val fecha: Long? = null,
    val hora: String = ""
)

//pantallas de la app
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistro( //pantalla de registro
    navController: NavHostController,
    patientData: PatientData,
    onDataChange: (PatientData) -> Unit
) { //se utiliza para actualizar los datos del paciente
    var nombre by remember { mutableStateOf(patientData.nombre) }
    var telefono by remember { mutableStateOf(patientData.telefono) }
    var nombreError by remember { mutableStateOf("") }
    var telefonoError by remember { mutableStateOf("") }

    // Validaciones
    fun validarNombre(value: String): Boolean {
        return if (value.trim().split(" ").filter { it.isNotEmpty() }.size >= 2) { //separa el nombre y apellido
            nombreError = ""
            true
        } else {
            nombreError = "Debe ingresar nombre y apellido" //muestra el error
            false
        }
    }

    fun validarTelefono(value: String): Boolean { //se utiliza para validar el telefono
        return if (value.matches(Regex("^[0-9]{10}$"))) {
            telefonoError = ""
            true
        } else {
            telefonoError = "Debe tener 10 dígitos numéricos" //muestra el error
            false
        }
    }

    //valida que los datos se encuentran correctamente ingresados
    val esValido = validarNombre(nombre) && validarTelefono(telefono) && nombre.isNotEmpty() && telefono.isNotEmpty()

    //Estilos
    Column( //se utiliza para alinear los elementos horizontalmente y verticalmente
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Clínica Salud Perfecta",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = "Registro del Paciente",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                if (it.isNotEmpty()) validarNombre(it)
            },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth(),
            isError = nombreError.isNotEmpty(),
            supportingText = if (nombreError.isNotEmpty()) {
                { Text(nombreError, color = MaterialTheme.colorScheme.error) }
            } else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = {
                if (it.all { char -> char.isDigit() } && it.length <= 10) {
                    telefono = it
                    if (it.isNotEmpty()) validarTelefono(it)
                }
            },
            label = { Text("Número de Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            isError = telefonoError.isNotEmpty(),
            supportingText = if (telefonoError.isNotEmpty()) {
                { Text(telefonoError, color = MaterialTheme.colorScheme.error) }
            } else null
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onDataChange(patientData.copy(nombre = nombre, telefono = telefono))
                navController.navigate("fecha_hora")
            },
            enabled = esValido, //se utiliza para habilitar o deshabilitar el boton
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Siguiente", fontSize = 16.sp)
        }
    }
}

//pantalla de fecha y hora
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFechaHora( //se utiliza para actualizar los datos del paciente
    navController: NavHostController,
    patientData: PatientData,
    onDataChange: (PatientData) -> Unit
) { //se utiliza para actualizar los datos del paciente
    var selectedDate by remember { mutableStateOf(patientData.fecha) }
    var selectedTime by remember { mutableStateOf(patientData.hora) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState( //se utiliza para mostrar el datepicker
        initialSelectedDateMillis = selectedDate ?: System.currentTimeMillis()
    )

    val timePickerState = rememberTimePickerState( //se utiliza para mostrar el timepicker
        initialHour = if (selectedTime.isNotEmpty()) selectedTime.split(":")[0].toInt() else 8,
        initialMinute = if (selectedTime.isNotEmpty()) selectedTime.split(":")[1].toInt() else 0
    )

    // Horarios disponibles (8:00 AM a 6:00 PM en intervalos de 30 minutos)
    val availableTimes = generateTimeSlots()

    //Estilos
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Seleccionar Fecha y Hora",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Fecha de la Cita",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = { showDatePicker = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (selectedDate != null) {
                            SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", Locale("es", "ES"))
                                .format(Date(selectedDate!!))
                        } else {
                            "Seleccionar Fecha"
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Hora de la Cita",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = { showTimePicker = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (selectedTime.isNotEmpty()) {
                            formatTime(selectedTime)
                        } else {
                            "Seleccionar Hora"
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onDataChange(
                    patientData.copy(
                        fecha = selectedDate,
                        hora = selectedTime
                    )
                )
                navController.navigate("confirmacion") //se utiliza para navegar a la pantalla de confirmacion
            },
            enabled = selectedDate != null && selectedTime.isNotEmpty(), //se utiliza para habilitar o deshabilitar el boton
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Confirmar Cita", fontSize = 16.sp)
        }
    }

    // Muestra el datepicker o el timepicker según corresponda
    if (showDatePicker) {
        DatePickerDialog(
            onDateSelected = { dateMillis ->
                // Solo permitir fechas de hoy en adelante
                val today = System.currentTimeMillis()
                if (dateMillis != null && dateMillis >= today - 86400000) { // 86400000 milisegundos = 1 día
                    selectedDate = dateMillis
                }
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false },
            datePickerState = datePickerState
        )
    }

    if (showTimePicker) { //se utiliza para mostrar el timepicker
        TimePickerDialog(
            onTimeSelected = { hour, minute ->
                // Validar que la hora esté en el rango permitido (8-18)
                if (hour >= 8 && hour <= 18) {
                    val time = String.format("%02d:%02d", hour, minute) //se utiliza para formatear la hora
                    if (availableTimes.contains(time)) {
                        selectedTime = time
                    }
                }
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false },
            timePickerState = timePickerState
        )
    }
}

//Dialogs
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    datePickerState: DatePickerState
) {
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onDateSelected(datePickerState.selectedDateMillis) }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit,
    timePickerState: TimePickerState
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onTimeSelected(timePickerState.hour, timePickerState.minute)
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        text = {
            TimePicker(state = timePickerState)
        }
    )
}

//pantalla de confirmacion
@Composable
fun PantallaConfirmacion(patientData: PatientData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Confirmación",
            tint = Color.Green,
            modifier = Modifier
                .size(80.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "¡Cita Agendada Exitosamente!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Resumen de la Cita",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

                InfoRow(label = "Paciente:", value = patientData.nombre)
                InfoRow(label = "Teléfono:", value = patientData.telefono)
                InfoRow(
                    label = "Fecha:",
                    value = if (patientData.fecha != null) {
                        SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", Locale("es", "ES"))
                            .format(Date(patientData.fecha))
                    } else ""
                )
                InfoRow(
                    label = "Hora:",
                    value = formatTime(patientData.hora)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Por favor, llegue 15 minutos antes de su cita",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun HorizontalDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.fillMaxWidth(),
        color = Color.LightGray,
        thickness = 1.dp
    )
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.End
        )
    }
}

//se utiliza para generar los horarios disponibles
fun generateTimeSlots(): List<String> {
    val times = mutableListOf<String>()
    for (hour in 8..17) { // 8 AM a 5:30 PM
        times.add(String.format("%02d:00", hour))
        if (hour < 17) { // No agregar :30 para las 5 PM
            times.add(String.format("%02d:30", hour))
        }
    }
    return times
}

//se utiliza para formatear la hora
fun formatTime(time: String): String {
    if (time.isEmpty()) return ""
    val parts = time.split(":")
    val hour = parts[0].toInt()
    val minute = parts[1]
    val amPm = if (hour < 12) "AM" else "PM"
    val displayHour = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour
    return "$displayHour:$minute $amPm"
}