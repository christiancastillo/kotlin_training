package com.android.training.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var userInput: EditText? = null
    private var button: Button? = null
    private var textView: TextView? = null
    private var numTimesClicked: Int = 0
    private var texto: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_click)
        userInput = findViewById<EditText>(R.id.editTextPersonName)
        button = findViewById<Button>(R.id.buttonCounter)
        textView = findViewById<TextView>(R.id.textViewCounter)

        //texto = userInput?.getText().toString()
        textView?.setMovementMethod(ScrollingMovementMethod())
        button?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                numTimesClicked += 1
                textView?.append("\n the button got tapped $numTimesClicked times. \t Information: ${userInput?.getText().toString()}.") //agrega una nueva linea
            }

        })
    }
}