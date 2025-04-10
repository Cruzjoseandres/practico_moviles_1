package com.example.practico_1_moviles.Models
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class TareasListas(
    val titulo: String,
    val descripcion: String,
    var estado: Boolean,
    val color: Int
):  Serializable
