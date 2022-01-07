package com.android.training.valesalmacenj16

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding

class SearchMedicineList : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var binding: ActivitySearchMedicineListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search_medicine_list)
        setContentView(binding.getRoot())
        //val arregloJSON = arrayOf("Paracetamol", "Ibuprofeno", "Naproxeno")
        //adapter = ArrayAdapter(this, binding.autoCompleteTextView.getId()!!,arregloJSON)
        //binding.autoCompleteTextView.setAdapter(adapter)
    }
}