package com.android.training.valesalmacenj16

import android.R
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.training.valesalmacenj16.classes.MedicamentosModel
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding
import com.android.training.valesalmacenj16.classes.JsonUtils
import com.android.training.valesalmacenj16.classes.MyAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.concurrent.thread

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
            val listaMedicamentos = mutableListOf("")
            //val adapterGridView = MyAdapter(this@SearchMedicineList,)
            var presentacion : String
            var clave : String
            var descripcion: String
            var i : Int = 0

            while(i < medicamentos.size){
                arrayDescripcion.add(medicamentos[i].descr)
                arrayPresentacion.add(medicamentos[i].presentacion)
                arrayClave.add(medicamentos[i].clave)
                i++
            }

            binding.aCTVDescripcion.setThreshold(1) //empieza a trabajar desde el primer caracter
            binding.aCTVDescripcion.setAdapter(ArrayAdapter(this, R.layout.simple_list_item_1, arrayDescripcion))
            binding.aCTVDescripcion.setOnItemClickListener { parent, view, position, id ->
                var j : Int = 0
                while(j < medicamentos.size){
                    if(parent.getItemAtPosition(position).toString().equals(medicamentos[j].descr)){
                        binding.textViewClaveJSON.setText(medicamentos[j].clave)
                        binding.textViewPresentacionJSON.setText(medicamentos[j].presentacion)
                        break
                    }
                    j++
                }
            }

            binding.buttonAgregarMedicamento.setOnClickListener{
                if(binding.aCTVDescripcion.getText().toString().isEmpty()){
                    AlertDialog.Builder(this).setTitle("Alerta").setMessage("Ingrese información valida.").setPositiveButton("OK",
                        DialogInterface.OnClickListener { dialog, which ->  }).show()
                } else {
                    listaMedicamentos.add(binding.aCTVDescripcion.getText().toString())
                }
            }

            //TODO: Queda pendiente hacer el adapter recycler para que funcione con el RecyclerView
            //liga de interés: https://handyopinion.com/basic-recyclerview-custom-adapter-in-kotlin-android/
            //https://handyopinion.com/how-to-show-vertical-list-in-kotlin-using-recyclerview-example/
            thread(true){
                binding.recyclerViewMedicamentosLista.setLayoutManager(LinearLayoutManager(this))
                binding.recyclerViewMedicamentosLista.setAdapter(MyAdapter(this,listaMedicamentos))
            }

            //TODO: Implementar método para generar reporte en PDF y guardarlo en disco local (almacenamiento interno)
        } catch(e: Exception){
            Log.e(TAG,"ERROR: ${e.message}\n")
            e.printStackTrace()
        }
    }
}