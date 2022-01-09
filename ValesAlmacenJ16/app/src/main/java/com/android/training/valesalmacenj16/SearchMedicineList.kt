package com.android.training.valesalmacenj16

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding

class SearchMedicineList : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<Any>
    private lateinit var binding: ActivitySearchMedicineListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMedicineListBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_search_medicine_list)
        setContentView(binding.getRoot())
        val arregloJSON = arrayOf("Paracetamol", "Ibuprofeno", "Naproxeno")
        adapter = ArrayAdapter(this, R.layout.activity_search_medicine_list, arregloJSON)
        binding.autoCompleteTextView.setAdapter(adapter)
    }
}