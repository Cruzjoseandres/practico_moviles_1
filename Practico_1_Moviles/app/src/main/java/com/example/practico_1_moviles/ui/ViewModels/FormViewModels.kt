package com.example.practico_1_moviles.ui.ViewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico_1_moviles.Models.TareasListas
import com.example.practico_1_moviles.repositories.TareasRepository

class FormViewModels : ViewModel() {
    private val _navegarMainActivity = MutableLiveData<Boolean>()
    val navegarMainActivity: LiveData<Boolean> = _navegarMainActivity

     var selectedColor: Int = 0

    private val _tareaDetail = MutableLiveData<TareasListas?>()
    val tareaDetail: LiveData<TareasListas?> = _tareaDetail



    fun setColorButton(colorg: Int) {
        selectedColor = colorg
    }

    fun onBotonAgregarClicked() {
        _navegarMainActivity.value = true
    }

    fun resetNavegacion() {
        _navegarMainActivity.value = false
    }

    fun save(tarea: TareasListas?,titulo: String, descripcion: String) {
        println("selectedColor en save(): $selectedColor")

        saveTareas(tarea,titulo, descripcion, selectedColor.toInt())

    }

    private fun saveTareas(tarea: TareasListas? ,titulo: String, descripcion: String, color: Int) {
        if (tarea == null) {
            addTareas(titulo, descripcion, color)
            
            return
        }
        TareasRepository.saveTarea(
            TareasListas(
                titulo = titulo,
                descripcion = descripcion,
                estado = false,
                color = color
            )
        )
    }
    private fun addTareas(titulo: String, descripcion: String, color: Int) {
        TareasRepository.addTarea(
            TareasListas(
                titulo = titulo,
                descripcion = descripcion,
                estado = false,
                color = color
            )
        )
    }

     fun loadTareasDetail(tarea: TareasListas?) {
         _tareaDetail.value = tarea
     }


    fun deleteTarea(tarea: TareasListas?) {
        if (tarea != null) {
            TareasRepository.deleteTarea(tarea)
            _navegarMainActivity.value = true
        }
    }





}
