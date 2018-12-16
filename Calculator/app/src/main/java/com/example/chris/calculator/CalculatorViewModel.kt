package com.example.chris.calculator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    // Variables to hold the operands and type of calcualation
    private var operand1: Double? = null
    private var pendingOperation = "="

    val result = MutableLiveData<String>()
    val newNumber = MutableLiveData<String>()
    val operation = MutableLiveData<String>()

    fun digitPressed(caption: String) {
        if (newNumber.value != null) {
            newNumber.value = newNumber.value + caption
        }else {
            newNumber.value = caption
        }
    }

    fun operandPressed(op:String){
        try {
            val value = newNumber.value?.toDouble()
            if(value != null) {
                performOperation(value, op)
            }
        } catch (e: NumberFormatException) {
            newNumber.value = ""
        }
        pendingOperation = op
        operation.value = pendingOperation
    }

    fun negPressed() {
        val value = newNumber.value
        if (value == null || value.isEmpty()) {
            newNumber.value = "-"
        } else {
            try {
                var doubleValue = value.toDouble();
                doubleValue *= -1
                newNumber.value = doubleValue.toString()
            } catch (e: NumberFormatException) {
                //newNumber was "-" or ".", so clear it
                newNumber.value = ""
            }
        }
    }

    fun clrPressed() {
        result.value = ""
        newNumber.value = ""
        operand1 = null
        pendingOperation = ""
        operation.value = pendingOperation
    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN // handle attempt to divide by zero
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        result.value = (operand1.toString())
        newNumber.value = ""
    }
}