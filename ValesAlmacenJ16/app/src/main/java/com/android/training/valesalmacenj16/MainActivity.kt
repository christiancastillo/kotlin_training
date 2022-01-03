package com.android.training.valesalmacenj16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var buttonSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //1
        setContentView(R.layout.activity_main) //2
        buttonSearch = findViewById(R.id.buttonSearch) //3: Definicion de variables
        buttonSearch.setOnClickListener {
            val searchMedicineActivity = Intent(this, SearchMedicineList::class.java) //3: Definicion de variables e intents
            startActivity(searchMedicineActivity)
        }
    }
}