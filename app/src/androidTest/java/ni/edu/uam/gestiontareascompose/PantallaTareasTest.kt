package ni.edu.uam.gestiontareascompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import ni.edu.uam.gestiontareascompose.ui.PantallaTareas
import org.junit.Rule
import org.junit.Test

class PantallaTareasTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun agregarTarea_aparecePantalla() {

        composeRule.setContent {
            PantallaTareas()
        }

        composeRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Estudiar")

        composeRule
            .onNodeWithTag("btnAgregar")
            .performClick()

        composeRule
            .onNodeWithText("Estudiar")
            .assertExists()
    }

    @Test
    fun botonAgregar_respondeClick() {

        composeRule.setContent {
            PantallaTareas()
        }

        composeRule
            .onNodeWithTag("btnAgregar")
            .performClick()
    }

    @Test
    fun campoEntrada_aceptaTexto() {

        composeRule.setContent {
            PantallaTareas()
        }

        composeRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Nueva tarea")

        composeRule
            .onNodeWithTag("campoTitulo")
            .assertTextContains("Nueva tarea")
    }
}