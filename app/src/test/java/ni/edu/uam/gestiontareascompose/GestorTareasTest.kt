package ni.edu.uam.gestiontareascompose

import ni.edu.uam.gestiontareascompose.data.GestorTareas
import ni.edu.uam.gestiontareascompose.model.Tarea
import org.junit.Assert.*
import org.junit.Test

class GestorTareasTest {

    @Test
    fun agregarTarea_incrementaLista() {

        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(1,"Comprar","")
        )

        assertEquals(
            1,
            gestor.obtenerTodas().size
        )
    }

    @Test
    fun eliminarTarea_desapareceLista() {

        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(1,"Comprar","")
        )

        gestor.eliminarTarea(1)

        assertTrue(
            gestor.obtenerTodas().isEmpty()
        )
    }

    @Test
    fun completarTarea_cambiaEstado() {

        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(1,"Comprar","")
        )

        gestor.completarTarea(1)

        assertTrue(
            gestor.obtenerTodas()[0].completada
        )
    }

    @Test
    fun contarPendientes_retornaValorCorrecto() {

        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(1,"Tarea 1","")
        )

        gestor.agregarTarea(
            Tarea(2,"Tarea 2","")
        )

        gestor.completarTarea(1)

        assertEquals(
            1,
            gestor.contarPendientes()
        )
    }

    @Test
    fun listaVacia_retornaCeroPendientes() {

        val gestor = GestorTareas()

        assertEquals(
            0,
            gestor.contarPendientes()
        )
    }

    @Test
    fun pruebaNegativa_fallaIntencionalmente() {

        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(1,"Comprar","")
        )

        assertEquals(
            1,
            gestor.obtenerTodas().size
        )
    }
}