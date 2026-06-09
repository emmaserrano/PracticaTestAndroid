package ni.edu.uam.gestiontareascompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ni.edu.uam.gestiontareascompose.model.Tarea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTareas() {

    var titulo by remember { mutableStateOf("") }

    var filtro by remember {
        mutableStateOf("Todas")
    }

    val tareas = remember {
        mutableStateListOf<Tarea>()
    }

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

            TopAppBar(
                title = {
                    Text("Gestión de Tareas")
                }
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = {
                    Text("Nueva tarea")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("campoTitulo")
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                    if (titulo.isNotBlank()) {

                        tareas.add(
                            Tarea(
                                id = tareas.size + 1,
                                titulo = titulo
                            )
                        )

                        titulo = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("btnAgregar")
            ) {
                Text("Agregar tarea")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card {

                Column(
                    modifier = Modifier.padding(12.dp)
                ) {

                    Text(
                        "Pendientes: ${tareas.count { !it.completada }}",
                        modifier = Modifier.testTag("txtPendientes")
                    )

                    Text(
                        "Completadas: ${tareas.count { it.completada }}"
                    )

                    val porcentaje =
                        if (tareas.isEmpty()) 0
                        else (tareas.count { it.completada } * 100 / tareas.size)

                    Text(
                        "Progreso: $porcentaje%"
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                FilterChip(
                    selected = filtro == "Todas",
                    onClick = { filtro = "Todas" },
                    label = { Text("Todas") }
                )

                FilterChip(
                    selected = filtro == "Pendientes",
                    onClick = { filtro = "Pendientes" },
                    label = { Text("Pendientes") }
                )

                FilterChip(
                    selected = filtro == "Completadas",
                    onClick = { filtro = "Completadas" },
                    label = { Text("Completadas") }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {

                items(tareasFiltradas) { tarea ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            Row {

                                Checkbox(
                                    checked = tarea.completada,
                                    onCheckedChange = {
                                        tarea.completada = it
                                    }
                                )

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )

                                Text(
                                    text = tarea.titulo,
                                    textDecoration =
                                        if (tarea.completada)
                                            TextDecoration.LineThrough
                                        else
                                            TextDecoration.None
                                )
                            }

                            IconButton(
                                onClick = {
                                    tareas.remove(tarea)
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}