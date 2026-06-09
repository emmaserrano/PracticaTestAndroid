package ni.edu.uam.gestiontareascompose.data

import ni.edu.uam.gestiontareascompose.model.Tarea

class GestorTareas {

    private val tareas = mutableListOf<Tarea>()

    fun agregarTarea(tarea: Tarea) {
        tareas.add(tarea)
    }

    fun eliminarTarea(id: Int) {
        tareas.removeIf { it.id == id }
    }

    fun completarTarea(id: Int) {
        tareas.find { it.id == id }?.completada = true
    }

    fun obtenerTodas(): List<Tarea> = tareas

    fun obtenerPendientes(): List<Tarea> =
        tareas.filter { !it.completada }

    fun contarPendientes(): Int =
        tareas.count { !it.completada }

    fun obtenerCompletadas(): List<Tarea> =
        tareas.filter { it.completada }

    fun ordenarAlfabeticamente(): List<Tarea> =
        tareas.sortedBy { it.titulo }

    fun porcentajeCompletadas(): Double {

        if (tareas.isEmpty()) return 0.0

        return tareas.count { it.completada }
            .toDouble() * 100 / tareas.size
    }
}