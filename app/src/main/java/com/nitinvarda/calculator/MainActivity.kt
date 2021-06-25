package com.nitinvarda.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        output.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        output.text = ""
        lastDot= false
        lastNumeric = false

    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            output.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatedAdded(output.text.toString())){
            output.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }


    private fun isOperatedAdded(value:String) : Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("-") || value.contains(("*")) || value.contains("+")
                    ||value.contains("/")
        }

    }

    private  fun removeZeroAfterDot(result : String) : String{
        var value = result
        if(result.contains(".0")){
            value =  result.substring(0,result.length-2)
        }
        return value

    }

    fun onEqual(view: View) {
        if(lastNumeric) {
            var resultText = output.text.toString()
            var prefix = ""

            try {
                if(resultText.startsWith("-")){
                    prefix="-"
                    resultText = resultText.substring(1)
                }

                if(resultText.contains("-")){
                    val afterSplit = resultText.split("-")

                    var firstValue = afterSplit[0]
                    var secondValue =afterSplit[1]
                    if(!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }
                    output.text = removeZeroAfterDot((firstValue.toDouble() - secondValue.toDouble()).toString())
//
                }else if(resultText.contains("+")){
                    val afterSplit = resultText.split("+")

                    var firstValue = afterSplit[0]
                    var secondValue =afterSplit[1]
                    if(!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }
                    output.text = removeZeroAfterDot((firstValue.toDouble() + secondValue.toDouble()).toString())
//
                }else if(resultText.contains("/")){
                    val afterSplit = resultText.split("/")

                    var firstValue = afterSplit[0]
                    var secondValue =afterSplit[1]
                    if(!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }
                    output.text = removeZeroAfterDot((firstValue.toDouble() / secondValue.toDouble()).toString())
//
                }else if(resultText.contains("*")){
                    val afterSplit = resultText.split("*")

                    var firstValue = afterSplit[0]
                    var secondValue =afterSplit[1]
                    if(!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }
                    output.text = removeZeroAfterDot((firstValue.toDouble() * secondValue.toDouble()).toString())
//
                }



            }catch (e:ArithmeticException){
                e.printStackTrace()
            }



        }
    }


}