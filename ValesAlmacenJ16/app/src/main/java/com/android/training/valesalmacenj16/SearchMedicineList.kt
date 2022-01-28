package com.android.training.valesalmacenj16

import com.android.training.valesalmacenj16.R
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.training.valesalmacenj16.classes.MedicamentosModel
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding
import com.android.training.valesalmacenj16.classes.JsonUtils
import com.android.training.valesalmacenj16.classes.MyAdapter
import com.android.training.valesalmacenj16.databinding.RowItemBinding
import com.android.training.valesalmacenj16.classes.Parameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.concurrent.thread

class SearchMedicineList : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<Any>
    private lateinit var binding: ActivitySearchMedicineListBinding
    private lateinit var rowItem: RowItemBinding
    private val TAG = "SearchMedicineList"

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            setTitle("Busqueda de insumos")
            binding = ActivitySearchMedicineListBinding.inflate(layoutInflater)
            rowItem = RowItemBinding.inflate(layoutInflater)
            setContentView(binding.getRoot())
            val utilsJSON = JsonUtils()
            val jsonFileString = utilsJSON.getJsonDataFromAssets(getApplicationContext(),"medicamentos.json")
            val gson = Gson()
            val listMedicamentosType = object : TypeToken<List<MedicamentosModel>>(){}.getType()
            val medicamentos: List<MedicamentosModel> = gson.fromJson(jsonFileString,listMedicamentosType)
            val arrayDescripcion = mutableListOf("")
            val arrayPresentacion = mutableListOf("")
            val arrayClave = mutableListOf("")
            //val adapterGridView = MyAdapter(this@SearchMedicineList,)
            var presentacion : String
            var clave : String
            var descripcion: String
            var i : Int = 0
            val valores : Parameters = Parameters()

            while(i < medicamentos.size){
                arrayDescripcion.add(medicamentos[i].descr)
                arrayPresentacion.add(medicamentos[i].presentacion)
                arrayClave.add(medicamentos[i].clave)
                i++
            }

            binding.aCTVDescripcion.setThreshold(1) //empieza a trabajar desde el primer caracter
            binding.aCTVDescripcion.setAdapter(ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayDescripcion))
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

            //thread(true){
                binding.buttonAgregarMedicamento.setOnClickListener{
                    if(binding.aCTVDescripcion.getText().toString().isEmpty()){
                        AlertDialog.Builder(this).setTitle("Alerta").setMessage("Ingrese información valida.").setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialog, which ->  }).show()
                    } else {
                        AlertDialog.Builder(this).setTitle("Guardar clave?").setMessage("Se guardará la clave ${binding.aCTVDescripcion.getText().toString()}")
                            .setPositiveButton("OK",DialogInterface.OnClickListener{
                                dialog, id ->
//                                binding.recyclerViewMedicamentosLista.setLayoutManager(LinearLayoutManager(this))
//                                binding.recyclerViewMedicamentosLista.setAdapter(MyAdapter(rowItem.descripcionTvGrid,this, listaMedicamentos,rowItem.descripcionTvGrid.id))
//                                binding.recyclerViewMedicamentosLista.setAdapter(MyAdapter(rowItem.presentacionTvGrid, this, listaPresentaciones,rowItem.presentacionTvGrid.id))
//                                binding.recyclerViewMedicamentosLista.setAdapter(MyAdapter(rowItem.claveTvGrid, this, listaClaves,rowItem.claveTvGrid.id))
//                                MyAdapter(null,this, listaMedicamentos,null).notifyItemInserted(binding.recyclerViewMedicamentosLista.size)

                                val mActivityIntent = Intent(this, MainActivity::class.java) //con esto regresará al main activity
                                mActivityIntent.putExtra("descripcion",binding.aCTVDescripcion.getText().toString())
                                mActivityIntent.putExtra("claveInsumo",binding.textViewClaveJSON.getText().toString())
                                mActivityIntent.putExtra("presentacion",binding.textViewPresentacionJSON.getText().toString())
                                startActivity(mActivityIntent)
                                //finish() //termina la actividad
                            })
                            .setNegativeButton("Salir",DialogInterface.OnClickListener { dialog, which ->
                                //no hace nada
                            }).show()
                    }
                }
            //TODO: Implementar método para generar reporte en PDF y guardarlo en disco local (almacenamiento interno)
        } catch(e: Exception){
            Log.e(TAG,"ERROR: ${e.message}\n")
            e.printStackTrace()
        }
    }
}