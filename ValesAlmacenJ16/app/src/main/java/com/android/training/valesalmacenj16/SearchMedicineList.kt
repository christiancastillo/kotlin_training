package com.android.training.valesalmacenj16

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.KeyListener
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
            setContentView(binding.getRoot())
            var utilsJSON = JsonUtils()
            val jsonFileString = utilsJSON.getJsonDataFromAssets(getApplicationContext(),"medicamentos.json")
            val gson = Gson()
            val listMedicamentosType = object : TypeToken<List<MedicamentosModel>>(){}.getType()
            val medicamentos: List<MedicamentosModel> = gson.fromJson(jsonFileString,listMedicamentosType)
            var arrayDescripcion : List<String> = listOf()
            var arrayPresentacion: List<String> = listOf()
            var arrayClave : List<String> = listOf()
            var i : Int = 0
            var descString : String = ""
            var descPresentacion: String = ""
            var descClave : String = ""

            while(i < medicamentos.size){
                arrayDescripcion = listOf(medicamentos[i].descr)
                arrayPresentacion  = listOf(medicamentos[i].presentacion)
                arrayClave = listOf(medicamentos[i].clave)

                Log.i(TAG,"Info: ${binding.aCTVDescripcion.getText().toString()}")
                if (arrayDescripcion[i].contains(binding.aCTVDescripcion.getText().toString())){
                    descClave = arrayClave[i]
                    descPresentacion = arrayPresentacion[i]
                    break
                }
                Log.i(TAG,"******************************************************")
                Log.i(TAG,"valor de i: ${i}, descripcion: ${medicamentos[i].descr}, presentacion: ${medicamentos[i].presentacion}, clave: ${medicamentos[i].clave}")
                Log.i(TAG,"******************************************************")
                i++
            }
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayDescripcion)
            binding.aCTVDescripcion.setThreshold(1) //empieza a trabajar desde el primer caracter
            binding.aCTVDescripcion.setAdapter(adapter)
            binding.aCTVDescripcion.setText(binding.aCTVDescripcion.getAdapter().getItem(0).toString());
            binding.aCTVDescripcion.setOnItemClickListener { parent, view, position, id ->
                //TODO: Falta implementar código para listener cuando se da click en item.
            }

            //binding.textViewPresentacionJSON.setText(descPresentacion)
            //binding.textViewClaveJSON.setText(descClave)

            //TODO: Implementar método para convertir de un JSON a un objeto de la clase y también alimentar un arreglo
        } catch(e: Exception){
            Log.e("ONCREATE MEDTHOD:","********************************************")
            e.printStackTrace()
        }
    }
}