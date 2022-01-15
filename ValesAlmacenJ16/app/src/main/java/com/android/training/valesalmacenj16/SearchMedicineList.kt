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

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            binding = ActivitySearchMedicineListBinding.inflate(layoutInflater)
            //setContentView(R.layout.activity_search_medicine_list)
            setContentView(binding.getRoot())
            var utilsJSON = JsonUtils()
            val jsonFileString = utilsJSON.getJsonDataFromAssets(getApplicationContext(),"medicamentos.json")
            Log.i("data in json: ",jsonFileString!!)
            val gson = Gson()
            val listMedicamentosType = object : TypeToken<List<MedicamentosModel>>(){}.getType()
            val medicamentos: List<MedicamentosModel> = gson.fromJson(jsonFileString,listMedicamentosType)

            //TODO: Implementar método para convertir de un JSON a un objeto de la clase y también alimentar un arreglo
            val arregloJSON = arrayOf("Paracetamol", "Ibuprofeno", "Naproxeno")
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloJSON)
            binding.autoCompleteTextView.setThreshold(1) //empieza a trabajar desde el primer caracter
            binding.autoCompleteTextView.setAdapter(adapter)
        } catch(e: Exception){
            Log.e("ONCREATE MEDTHOD:","********************************************")
            e.printStackTrace()
        }
    }
}