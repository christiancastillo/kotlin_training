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
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(){
    private lateinit var buttonSearch: Button
    private lateinit var editTextDateFSalida : EditText
    private lateinit var etDateCad : EditText
    private lateinit var editTextLote : EditText
    private lateinit var checkNoCaduca :  CheckBox
    private lateinit var checkSinLote : CheckBox
    private lateinit var spinProc : Spinner
    private lateinit var fechaCal : String
    private lateinit var etDescripcionMed : EditText
    private lateinit var etClaveMed : EditText
    private lateinit var etPresentacionMed : EditText
    private var presentacionMed : String? = ""
    private var claveMed : String? = ""
    private var descripcionMed : String? = ""


    //private lateinit var binding: ActivitySearchMedicineListBinding //el binding debe ser basado en el nombre del activity!

    

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState) //1
      //      val view: View = binding.getRoot() //llama al binding del activity
            setContentView(R.layout.activity_main)
            setTitle("Generador de vales PDF")//2
            //3: Definicion de variables
            claveMed = intent.getStringExtra("claveInsumo")
            presentacionMed = intent.getStringExtra("presentacion")
            descripcionMed = intent.getStringExtra("descripcion")

            buttonSearch = findViewById(R.id.buttonSearch)
            editTextDateFSalida = findViewById(R.id.editTextDateFechaSalida)
            checkNoCaduca = findViewById(R.id.checkBoxSincad)
            checkSinLote = findViewById(R.id.checkBoxLote)
            editTextLote = findViewById(R.id.editTextLote)
            spinProc = findViewById(R.id.spinnerProcedencia)
            etDateCad = findViewById(R.id.editTextDateCad)
            etPresentacionMed = findViewById(R.id.editTextPresentacion)
            etClaveMed = findViewById(R.id.editTextClave)
            etDescripcionMed = findViewById(R.id.editTextDescripcion)


            //Se crea un arrayadapter para el spinner
            //mas informacion: https://developer.android.com/guide/topics/ui/controls/spinner

            var procedenciaStrings = arrayOf("Almacen Estatal", "Otra institución", "Jurisdicción", "Centro de Salud")
            val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,procedenciaStrings)
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinProc.setAdapter(arrayAdapter)

            etDescripcionMed.setText(descripcionMed)
            etClaveMed.setText(claveMed)
            etPresentacionMed.setText(presentacionMed)




        //    binding.textView.setText("LLAMADA DESDE BINDING")
            buttonSearch.setOnClickListener {
                //cambia de actividad
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
                    etDateCad.setText("NO CADUCA")
                } else {
                    etDateCad.setText("")
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
        var fecha = "${day}/${month+1}/${year}"
        etDateCad.setText(fecha)
        editTextDateFSalida.setText(fecha)
    }
    }