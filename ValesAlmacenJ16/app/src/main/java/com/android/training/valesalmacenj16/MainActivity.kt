package com.android.training.valesalmacenj16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding

class MainActivity : AppCompatActivity(){
    private lateinit var buttonSearch: Button
    //private lateinit var binding: ActivitySearchMedicineListBinding //el binding debe ser basado en el nombre del activity!

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState) //1
      //      val view: View = binding.getRoot() //llama al binding del activity
            setContentView(R.layout.activity_main) //2
            buttonSearch = findViewById(R.id.buttonSearch) //3: Definicion de variables
        //    binding.textView.setText("LLAMADA DESDE BINDING")
            buttonSearch.setOnClickListener {
                val searchMedicineListActivity = Intent(
                    this, SearchMedicineList::class.java
                ) //3: Definicion de variables e intents
                startActivity(searchMedicineListActivity)
            }
        } catch (e: Exception){
            Log.d("ERROR main activity: ","Error: " + e.message)
                //Toast.makeText(this, "Error: " + e.message, Toast.LENGTH_LONG).show()
            }
            //Toast.makeText(this,"Error: "+e.message,Toast.LENGTH_LONG).show()
        }
    }