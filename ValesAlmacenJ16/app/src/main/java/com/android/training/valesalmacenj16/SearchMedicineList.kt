package com.android.training.valesalmacenj16

import android.R
import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.KeyListener
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.android.training.valesalmacenj16.classes.MedicamentosModel
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding
import com.android.training.valesalmacenj16.classes.JsonUtils
import com.android.training.valesalmacenj16.classes.MyAdapter
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
            val utilsJSON = JsonUtils()
            val jsonFileString = utilsJSON.getJsonDataFromAssets(getApplicationContext(),"medicamentos.json")
            val gson = Gson()
            val listMedicamentosType = object : TypeToken<List<MedicamentosModel>>(){}.getType()
            val medicamentos: List<MedicamentosModel> = gson.fromJson(jsonFileString,listMedicamentosType)
            val arrayDescripcion = mutableListOf("")
            val arrayPresentacion = mutableListOf("")
            val arrayClave = mutableListOf("")
            val arrayIds = mutableListOf("")
            //val adapterGridView = MyAdapter(this@SearchMedicineList,)
            var presentacion : String
            var clave : String
            var descripcion: String
            var i : Int = 0
            var posicion : Int = 0

            while(i < medicamentos.size){
                arrayIds.add(medicamentos[i].id.toString())
                arrayDescripcion.add(medicamentos[i].descr)
                arrayPresentacion.add(medicamentos[i].presentacion)
                arrayClave.add(medicamentos[i].clave)
                i++
            }

            adapter = ArrayAdapter(this, R.layout.simple_list_item_1, arrayDescripcion.toTypedArray())
            binding.aCTVDescripcion.setThreshold(1) //empieza a trabajar desde el primer caracter
            binding.aCTVDescripcion.setAdapter(adapter)
            binding.aCTVDescripcion.setOnItemClickListener { parent, view, position, id ->
                var j: Int = 0
                while(j < medicamentos.size){
                    if(parent.getItemAtPosition(position).toString().equals(medicamentos[j].descr)){
                        binding.textViewClaveJSON.setText(medicamentos[j].clave)
                        binding.textViewPresentacionJSON.setText(medicamentos[j].presentacion)
                        break
                    }
                    j++
                }
            }

            //binding.textViewPresentacionJSON.setText(descPresentacion)
            //binding.textViewClaveJSON.setText(descClave)

            //TODO: Implementar método para convertir de un JSON a un objeto de la clase y también alimentar un arreglo
            //TODO: Implementar método para generar reporte en PDF y guardarlo en disco local (almacenamiento interno)
        } catch(e: Exception){
            Log.e(TAG,"ERROR ******************************************** ERROR: ${e.message}\n")
            e.printStackTrace()
        }
    }
}