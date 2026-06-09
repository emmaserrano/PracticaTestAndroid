package ni.edu.uam.gestiontareascompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ni.edu.uam.gestiontareascompose.model.Prioridad
import ni.edu.uam.gestiontareascompose.model.Tarea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTareas() {

    var titulo by remember { mutableStateOf("") }

    var prioridadSeleccionada by remember {
        mutableStateOf(Prioridad.MEDIA)
    }

    var filtro by remember {
        mutableStateOf("Todas")
    }

    val tareas = remember {
        mutableStateListOf<Tarea>()
    }

    val pendientes = tareas.count { !it.completada }

    val porcentaje =
        if (tareas.isEmpty()) 0
        else (tareas.count { it.completada } * 100 / tareas.size)

    val tareasFiltradas = when (filtro) {

        "Pendientes" ->
            tareas.filter { !it.completada }

        "Completadas" ->
            tareas.filter { it.completada }

        else ->
            tareas.sortedBy { it.titulo }
    }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Gestión de Tareas",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Nueva tarea",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = titulo,
                        onValueChange = {
                            titulo = it
                        },
                        label = {
                            Text("Título")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("campoTitulo")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Prioridad")

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {

                        FilterChip(
                            selected = prioridadSeleccionada == Prioridad.ALTA,
                            onClick = {
                                prioridadSeleccionada = Prioridad.ALTA
                            },
                            label = { Text("Alta") }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        FilterChip(
                            selected = prioridadSeleccionada == Prioridad.MEDIA,
                            onClick = {
                                prioridadSeleccionada = Prioridad.MEDIA
                            },
                            label = { Text("Media") }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        FilterChip(
                            selected = prioridadSeleccionada == Prioridad.BAJA,
                            onClick = {
                                prioridadSeleccionada = Prioridad.BAJA
                            },
                            label = { Text("Baja") }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btnAgregar"),
                        onClick = {

                            if (titulo.isNotBlank()) {

                                tareas.add(
                                    Tarea(
                                        id = tareas.size + 1,
                                        titulo = titulo,
                                        prioridad = prioridadSeleccionada
                                    )
                                )

                                titulo = ""
                            }
                        }
                    ) {
                        Text("Agregar Tarea")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Resumen",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Pendientes: $pendientes",
                        modifier = Modifier.testTag("txtPendientes")
                    )

                    Text(
                        "Total: ${tareas.size}"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { porcentaje / 100f },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("Progreso: $porcentaje%")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {

                FilterChip(
                    selected = filtro == "Todas",
                    onClick = {
                        filtro = "Todas"
                    },
                    label = {
                        Text("Todas")
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                FilterChip(
                    selected = filtro == "Pendientes",
                    onClick = {
                        filtro = "Pendientes"
                    },
                    label = {
                        Text("Pendientes")
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                FilterChip(
                    selected = filtro == "Completadas",
                    onClick = {
                        filtro = "Completadas"
                    },
                    label = {
                        Text("Completadas")
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {

                items(tareasFiltradas) { tarea ->

                    TareaCard(
                        tarea = tarea,
                        onDelete = {
                            tareas.remove(tarea)
                        }
                    )
                }
            }
        }
    }
}