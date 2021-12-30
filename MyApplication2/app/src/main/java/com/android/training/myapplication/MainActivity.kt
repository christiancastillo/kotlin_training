package com.android.training.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

private const val TAG = "My Acvity"
private const val TEXT_CONTENTS = "TextContent"

class MainActivity : AppCompatActivity() {
    //private var userInput: EditTe
    //
    // xt? = null
    //private var button: Button? = null
    //private var textView: TextView? = null
    private var numTimesClicked: Int = 0
    private var texto: String = ""

    lateinit var textView: TextView //lateinit por que se va a inicializar tardiamente

    //val userInput: EditText = findViewById<EditText>(R.id.editTextPersonName)
    //val button: Button = findViewById<Button>(R.id.buttonCounter)
    //val textView: TextView = findViewById<TextView>(R.id.textViewCounter)

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_click)


        val userInput: EditText = findViewById<EditText>(R.id.editTextPersonName)
        val button: Button = findViewById<Button>(R.id.buttonCounter)
        //val textView: TextView = findViewById<TextView>(R.id.textViewCounter)
        textView = findViewById<TextView>(R.id.textViewCounter)
        texto = userInput.getText().toString()
        //textView?.setText("")
        textView.text = "" // es lo mismo que textView?.setText("")
        userInput.setText("")

        //texto = userInput?.getText().toString()
        textView.setMovementMethod(ScrollingMovementMethod())

        //textView?.movementMethod = ScrollingMovementMethod()
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick: called")
                textView.append(userInput.text) //agrega una nueva linea
                textView.append("\n")
                userInput.setText("")
            }
        })
    }



    override fun onStart() {
        Log.d(TAG, "onStart: Called")
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState: Called")
        super.onRestoreInstanceState(savedInstanceState)
        //val textView: TextView = findViewById<TextView>(R.id.textViewCounter)
        //val savedString: String = savedInstanceState.getString(TEXT_CONTENTS,"")
        //textView.setText(savedString)
        textView.text = savedInstanceState.getString(TEXT_CONTENTS,"")
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Called")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: Called")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Called")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //val textView: TextView = findViewById<TextView>(R.id.textViewCounter)
        Log.d(TAG, "onSaveInstanceState: Called")
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_CONTENTS,textView.getText().toString()) //guarda el contenido
    }
}

