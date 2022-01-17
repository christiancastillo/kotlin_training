package com.android.training.valesalmacenj16

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.android.training.valesalmacenj16.classes.MedicamentosModel
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding
import com.android.training.valesalmacenj16.classes.JsonUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchMedicineList : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<Any>
    private lateinit var binding: ActivitySearchMedicineListBinding
    private val TAG = "SearchMedicineList"

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            binding = ActivitySearchMedicineListBinding.inflate(layoutInflater)
            //setContentView(R.layout.activity_search_medicine_list)
            setContentView(binding.getRoot())
            var utilsJSON = JsonUtils()
            val jsonFileString = utilsJSON.getJsonDataFromAssets(getApplicationContext(),"medicamentos.json")
            val gson = Gson()
            val listMedicamentosType = object : TypeToken<List<MedicamentosModel>>(){}.getType()
            val medicamentos: List<MedicamentosModel> = gson.fromJson(jsonFileString,listMedicamentosType)
            Log.i(TAG,"******************************DATOS DEL JSON******************************")
            Log.i(TAG, medicamentos[0].presentacion)
            Log.i(TAG, medicamentos[0].clave)
            Log.i(TAG, medicamentos[0].descr)

            var arrayDescripcion : List<String> = listOf()
            var arrayPresentacion : List<String> = listOf()
            var arrayClave : List<String> = listOf()
            var i : Int = 0
            while(i < medicamentos.size){
                Log.i(TAG,"medicamentos.size: ${medicamentos.size}")
                Log.i(TAG, "valor de i: ${i}")
                arrayDescripcion = listOf(medicamentos[i].descr)
                arrayPresentacion = listOf(medicamentos[i].presentacion)
                arrayClave = listOf(medicamentos[i].clave)

                if (arrayDescripcion[i] == binding.aCTVDescripcion.getText().toString()){
                    binding.textViewClaveJSON.setText(arrayClave[i])
                    binding.textViewPresentacionJSON.setText(arrayPresentacion[i])
                    break
                }
                Log.i(TAG,"******************************************************")
                Log.i(TAG,"valor de i: ${i}, descripcion: ${medicamentos[i].descr}, presentacion: ${medicamentos[i].presentacion}, clave: ${medicamentos[i].clave}")
                Log.i(TAG,"******************************************************")
                i++
            }

            //TODO: Implementar método para convertir de un JSON a un objeto de la clase y también alimentar un arreglo

            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayDescripcion)
            binding.aCTVDescripcion.setThreshold(1) //empieza a trabajar desde el primer caracter
            binding.aCTVDescripcion.setAdapter(adapter)
        } catch(e: Exception){
            Log.e("ONCREATE MEDTHOD:","********************************************")
            e.printStackTrace()
        }
    }
}