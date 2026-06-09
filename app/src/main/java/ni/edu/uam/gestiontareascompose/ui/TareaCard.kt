package ni.edu.uam.gestiontareascompose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ni.edu.uam.gestiontareascompose.model.Prioridad
import ni.edu.uam.gestiontareascompose.model.Tarea

@Composable
fun TareaCard(
    tarea: Tarea,
    onDelete: () -> Unit
) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            if (tarea.completada)
                                "Completada"
                            else
                                "Pendiente"
                        )
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            tarea.prioridad.name
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = tarea.titulo,
                fontWeight = FontWeight.Bold,
                textDecoration =
                    if (tarea.completada)
                        TextDecoration.LineThrough
                    else
                        TextDecoration.None
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                "Creada: ${tarea.fecha}"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {

                FilledTonalButton(
                    onClick = {
                        tarea.completada =
                            !tarea.completada
                    }
                ) {

                    Icon(
                        Icons.Default.Check,
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text("Completar")
                }

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                OutlinedButton(
                    onClick = onDelete
                ) {

                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text("Eliminar")
                }
            }
        }
    }
}