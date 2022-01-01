package com.android.training.mycalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

private const val STATE_PENDING_OPERATION = "PENDINGOPERATION"
private const val STATE_OPERAND1 = "STATEOPERAND1"
private const val STATE_OPERAND1_STORE = "OPERAND_STORED"

class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.textViewOperation) } //esta es otra forma de inicializar variables, las inicializa y solo se pueden usar hasta que se utilice por primera vez

    //Variables to hold the operands and type of calculation
    private var operand1:Double?=null //puede ser de tipo null por que puede no recibir un valor desde la APP
    //private var operand2:Float=0.0F
    //private var operand2:Double=0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.editTextResult)
        newNumber = findViewById(R.id.editTextNewNumber)

        //Data input buttons

        val button0 = findViewById<Button>(R.id.button0)
        val button1: Button = findViewById<Button>(R.id.button1)
        val button2: Button = findViewById<Button>(R.id.button2)
        val button3: Button = findViewById<Button>(R.id.button3)
        val button4: Button = findViewById<Button>(R.id.button4)
        val button5: Button = findViewById<Button>(R.id.button5)
        val button6: Button = findViewById<Button>(R.id.button6)
        val button7: Button = findViewById<Button>(R.id.button7)
        val button8: Button = findViewById<Button>(R.id.button8)
        val button9: Button = findViewById<Button>(R.id.button9)
        val buttonDecimal: Button = findViewById<Button>(R.id.buttonDecimal)

        //Operations buttons
        val buttonEquals: Button = findViewById<Button>(R.id.buttonEquals)
        val buttonDivide: Button = findViewById<Button>(R.id.buttonDivide)
        val buttonMinus: Button = findViewById<Button>(R.id.buttonMinus)
        val buttonStar: Button = findViewById<Button>(R.id.buttonStar)
        val buttonPlus: Button = findViewById<Button>(R.id.buttonPlus)

        val listener = View.OnClickListener { value -> //lambda?
            val b = value as Button
            newNumber.append(b.text)
        }

        val opListener = View.OnClickListener { value ->
            val op = (value as Button).getText().toString()
            try{
                val value = newNumber.getText().toString().toDouble()
                performOperation(value,op)
            } catch(e: NumberFormatException){
                newNumber.setText("")
            }
            pendingOperation = op
            newNumber.setText("")
            //displayOperation.setText(pendingOperation)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDecimal.setOnClickListener(listener)
        buttonDivide.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
        buttonStar.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonEquals.setOnClickListener(opListener)
    }

    private fun performOperation(value: Double, operation: String){
        if (operand1 == null){
            operand1 = value
        } else {
            //operand2 = value.toDouble()
            if (pendingOperation == "="){
                pendingOperation = operation
            }
            when (pendingOperation){
                //"=" -> operand1 = operand2
                "=" -> operand1 = value
                "/" -> if (value == 0.0){
                            operand1 = Double.NaN //handle attempt to divide by zero
                        } else {
                            operand1 = operand1!! / value
                        //el operador de null safety es !!
                        }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        result.setText(operand1.toString())
        displayOperation.setText(operation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean(STATE_OPERAND1_STORE,false)){
            operand1 = savedInstanceState.getDouble(STATE_OPERAND1)
        } else {
            operand1 = null
        }
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1)
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION,"")
        displayOperation.setText(pendingOperation)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (operand1 != null){
            outState?.putDouble(STATE_OPERAND1,operand1!!) //safe call operator: ?
            outState.putBoolean(STATE_OPERAND1_STORE,true)
        }
        outState.putString(STATE_PENDING_OPERATION,pendingOperation)
    }
}