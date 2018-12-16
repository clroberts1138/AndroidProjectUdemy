package com.example.chris.calculator

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val TEXT_CONTENTS = "TextContent"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Oncreate: Called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        viewModel.result.observe(this,Observer<String>{ stringResult -> result.setText(stringResult)})
        viewModel.newNumber.observe(this, Observer<String> {stringNumber -> newNumber.setText(stringNumber)})
        viewModel.operation.observe(this,Observer<String> {stringOperation -> operation.text = stringOperation})

        val listener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
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
        buttonDot.setOnClickListener(listener)
        //btn_neg.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            viewModel.operandPressed((v as Button).text.toString())

        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)

        btnNeg.setOnClickListener {
            viewModel.negPressed()

        }

        btnClr.setOnClickListener {
            viewModel.clrPressed()
        }

    }
    // Debugging Activity Lifecycle Methods
    override fun onStart() {
        Log.d(TAG, "OnStart: Called")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "Oncreate: Called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Called")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: Called")
        super.onRestart()
    }


}

