package ni.edu.uam.gestiontareascompose.model

enum class Prioridad {
    ALTA,
    MEDIA,
    BAJA
}

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String = "",
    val fecha: String = "09/06/2026",
    val prioridad: Prioridad = Prioridad.MEDIA,
    var completada: Boolean = false
)