package com.android.training.valesalmacenj16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.android.training.valesalmacenj16.databinding.ActivitySearchMedicineListBinding

class MainActivity : AppCompatActivity(){
    private lateinit var buttonSearch: Button
    private lateinit var editTextDateFSalida : EditText
    private lateinit var editTextNC : EditText
    private lateinit var etDateCad : EditText
    private lateinit var editTextLote : EditText
    private lateinit var checkNoCaduca :  CheckBox
    private lateinit var checkSinLote : CheckBox
    private lateinit var listaProcedencia : ArrayAdapter<String>
    private lateinit var spinProc : Spinner
    //private lateinit var binding: ActivitySearchMedicineListBinding //el binding debe ser basado en el nombre del activity!

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState) //1
      //      val view: View = binding.getRoot() //llama al binding del activity
            setContentView(R.layout.activity_main) //2
            //3: Definicion de variables
            buttonSearch = findViewById(R.id.buttonSearch)
            editTextDateFSalida = findViewById(R.id.editTextDateFechaSalida)
            checkNoCaduca = findViewById(R.id.checkBoxSincad)
            checkSinLote = findViewById(R.id.checkBoxLote)
            editTextNC = findViewById(R.id.editTextDateCad)
            editTextLote = findViewById(R.id.editTextLote)
            spinProc = findViewById(R.id.spinnerProcedencia)

            //Se crea un arrayadapter para el spinner
            //mas informacion: https://developer.android.com/guide/topics/ui/controls/spinner

            /*ArrayAdapter.createFromResource(this,R.array.Procedencia,R.layout.activity_main).also{
                    adapter ->
                adapter.setDropDownViewResource(R.layout.activity_main)
                spinProc.adapter = adapter
            }*/
        //    binding.textView.setText("LLAMADA DESDE BINDING")
            buttonSearch.setOnClickListener {
                val searchMedicineListActivity = Intent(
                    this, SearchMedicineList::class.java
                ) //3: Definicion de variables e intents
                startActivity(searchMedicineListActivity)
            }

            editTextDateFSalida.setOnClickListener{
                showDatePicker()
            }

            checkSinLote.setOnClickListener{
                if (checkSinLote.isChecked()){
                    editTextLote.setText("NO APLICA")
                } else {
                    editTextLote.setText("")
                }
            }

            checkNoCaduca.setOnClickListener{
                if (checkNoCaduca.isChecked()){
                    editTextNC.setText("NO CADUCA")
                } else {
                    editTextNC.setText("")
                }
            }

            etDateCad.setOnClickListener {
                showDatePicker()
            }



        } catch (e: Exception){
            Log.e("ERROR main activity: ","Error: " + e.message)
                //Toast.makeText(this, "Error: " + e.message, Toast.LENGTH_LONG).show()
            }
        }

    //Funcion para datePicker
    private fun showDatePicker(){
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager,"datePicker")
        //ed.setText(fecha)
    }

    private fun onDateSelected(day: Int, month: Int, year: Int){
        val fecha = "${day}/${month+1}/${year}"
        editTextDateFSalida.setText(fecha)
    }
    }