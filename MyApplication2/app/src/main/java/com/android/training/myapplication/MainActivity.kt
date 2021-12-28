package com.android.training.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //private var userInput: EditText? = null
    //private var button: Button? = null
    //private var textView: TextView? = null
    private var numTimesClicked: Int = 0
    private var texto: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_click)


        val userInput: EditText = findViewById<EditText>(R.id.editTextPersonName)
        val button: Button = findViewById<Button>(R.id.buttonCounter)
        val textView: TextView = findViewById<TextView>(R.id.textViewCounter)
        texto = userInput?.getText().toString()
        //textView?.setText("")
        textView.text = "" // es lo mismo que textView?.setText("")
        userInput.setText("")

        //texto = userInput?.getText().toString()
        textView.setMovementMethod(ScrollingMovementMethod())
        //textView?.movementMethod = ScrollingMovementMethod()
        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                textView.append(userInput.text) //agrega una nueva linea
                textView.append("\n")
                userInput.setText("")
            }

        })
    }
}