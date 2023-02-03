package com.example.dovicaluculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }




    var isNewOp=true
    var dot=true
    var moins = false

    fun buNumberEvent(view: View)
    {
        if(isNewOp)
        {
            etShowNumber.setText("")
        }
        isNewOp=false
        val buSelect= view as Button
        var buClickValue:String=etShowNumber.text.toString()
        when(buSelect.id)
        {
            bu0.id->
            {
                buClickValue+="0"
            }
            bu1.id->
            {
                buClickValue+="1"
            }
            bu2.id->
            {
                buClickValue+="2"
            }
            bu3.id->
            {
                buClickValue+="3"
            }
            bu4.id->
            {
                buClickValue+="4"
            }
            bu5.id->
            {
                buClickValue+="5"
            }
            bu6.id->
            {
                buClickValue+="6"
            }
            bu7.id->
            {
                buClickValue+="7"
            }
            bu8.id->
            {
                buClickValue+="8"
            }
            bu9.id->
            {
                buClickValue+="9"
            }
            buDot.id->
            {
                if(dot==true)
                {
                    buClickValue += "."
                }
                dot=true
            }
            buPlusMinus.id->
            {
                if(moins==false)
                {
                    buClickValue="-" + buClickValue
                }
                moins=true

            }
        }
        etShowNumber.setText(buClickValue)
    }
    var op="*"
    var oldNumber=""

    fun buOpEvent(view: View)
    {
        val buSelect= view as Button
        when(buSelect.id)
        {
            buMul.id->
            {
                op="*"
            }
            buDiv.id->
            {
                op="÷"
            }
            buSub.id->
            {
                op="-"
            }
            buSum.id->
            {
                op="+"
            }
        }
        oldNumber=etShowNumber.text.toString()
        isNewOp=true
        dot=false

    }

    fun buEqualEvent(view: View)
    {
        val newNumber=etShowNumber.text.toString()
        var finalNumber:Double?=null
        when(op)
        {
            "*"->
            {
                finalNumber=oldNumber.toDouble() * newNumber.toDouble()
            }
            "÷"->
            {
                finalNumber=oldNumber.toDouble() / newNumber.toDouble()
            }
            "-"->
            {
                finalNumber=oldNumber.toDouble() - newNumber.toDouble()
            }
            "+"->
            {
                finalNumber=oldNumber.toDouble() + newNumber.toDouble()
            }
        }
        etShowNumber.setText(finalNumber.toString())
        isNewOp=true
    }

    fun buPercentEvent(view: View)
    {
        val number=(etShowNumber.text.toString().toDouble())/100
        etShowNumber.setText(number.toString())
        isNewOp=true
    }

    fun buCleanEvent(view: View)
    {
        etShowNumber.setText("")
        isNewOp=true
        dot=false
    }

     fun backSpaceAction(view: View) {
        val length = etShowNumber.length()
         etShowNumber.text = etShowNumber.text.subSequence(0, length -1)
    }
    }