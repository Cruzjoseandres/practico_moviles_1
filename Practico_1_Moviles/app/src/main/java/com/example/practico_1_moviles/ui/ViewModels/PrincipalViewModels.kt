package com.example.practico_1_moviles.ui.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalViewModels : ViewModel() {
    private val _navegarAMainActivity2 = MutableLiveData<Boolean>()
    val navegarMainActivity2: LiveData<Boolean> = _navegarAMainActivity2


    fun onBotonAgregarClicked() {
        _navegarAMainActivity2.value = true
    }

    fun resetNavegacion() {
        _navegarAMainActivity2.value = false
    }





}