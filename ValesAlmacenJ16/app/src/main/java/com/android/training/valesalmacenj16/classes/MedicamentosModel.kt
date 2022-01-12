package com.android.training.valesalmacenj16.classes

data class MedicamentosModel (
    val id: Int,
    val clave: String,
    val descr: String,
    val presentacion: String,
    val tipo: String
        )

/*
* Ligas de interes:

https://www.baeldung.com/kotlin/json-convert-data-class
https://www.tutorialspoint.com/how-to-read-files-from-assets-on-android-using-kotlin
https://stackoverflow.com/questions/45176941/how-to-parse-local-json-file-in-assets
https://stackoverflow.com/questions/23073627/json-array-to-auto-complete-text-view-in-android*/