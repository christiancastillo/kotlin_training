package com.android.training.valesalmacenj16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var buttonSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState) //1
            setContentView(R.layout.activity_main) //2
            buttonSearch = findViewById(R.id.buttonSearch) //3: Definicion de variables
            buttonSearch.setOnClickListener {
                val searchMedicineActivity = Intent(this, SearchMedicineActivity::class.java) //3: Definicion de variables e intents
                startActivity(searchMedicineActivity)
            }
        } catch(e: Exception){
            Toast.makeText(this,"Error: "+e.message,Toast.LENGTH_LONG).show()
        }
    }
}