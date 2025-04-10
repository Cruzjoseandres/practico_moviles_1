package com.example.practico_1_moviles.repositories

import com.example.practico_1_moviles.Models.TareasListas

object TareasRepository {
    private val tareas = arrayListOf(
        TareasListas("Tarea 1", "Descripcion 1", false, 0xFF0000),
        TareasListas("Tarea 2", "Descripcion 2", true, 0x00FF00),
        TareasListas("Tarea 3", "Descripcion 3", false, 0x0000FF)
    )

    fun getTareas(): ArrayList<TareasListas> {
        return tareas.clone() as ArrayList<TareasListas>
    }

    fun saveTarea(tarea: TareasListas) {
        val index = tareas.indexOfFirst { it.titulo == tarea.titulo }
        tareas[index] = tarea
    }

    fun addTarea(tarea: TareasListas) {
        tareas.add(tarea)
    }

    fun deleteTarea(tarea: TareasListas) {
        tareas.removeIf { it.titulo == tarea.titulo }
    }
}