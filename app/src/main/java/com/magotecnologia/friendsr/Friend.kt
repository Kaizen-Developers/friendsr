package com.magotecnologia.friendsr

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Marco-Laptop on 15/05/2020.
 * Clase que almacena los datos de las personas de la aplicacion
 */

@Parcelize
data class Friend(val name: String, val description: String, val photo: String, var score: Float) :
    Parcelable {
}