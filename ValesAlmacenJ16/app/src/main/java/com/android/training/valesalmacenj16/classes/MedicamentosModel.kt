package com.android.training.valesalmacenj16.classes

import com.google.gson.annotations.SerializedName

//esta clase esta hecha siguiendo el JSON

data class MedicamentosModel (
    @SerializedName("id") val id: Int,
    @SerializedName("clave") val clave: String,
    @SerializedName("descr") val descr: String,
    @SerializedName("presentacion") val presentacion: String,
    @SerializedName("tipo") val tipo: String
        )
/*
* Ligas de interes:

https://www.baeldung.com/kotlin/json-convert-data-class
https://www.tutorialspoint.com/how-to-read-files-from-assets-on-android-using-kotlin
https://stackoverflow.com/questions/45176941/how-to-parse-local-json-file-in-assets
https://stackoverflow.com/questions/23073627/json-array-to-auto-complete-text-view-in-android*/