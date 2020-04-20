package com.gmail.killian.calculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.children
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        Log.d("test", "test")

        val parent = findViewById<ConstraintLayout>(R.id.calcLayout)
        var calcPending: TextView
        var resultFinal: TextView
        var str: String

        val buttons = parent.children.filter {
            it is Button
        }

        buttons.forEach {
            it.setOnClickListener{
                Log.d("test", "test")
                calcPending = findViewById(R.id.calcPending)
                resultFinal = findViewById(R.id.calcResult)
                str = calcPending.text.toString()

                when (it.tag) {

                    "number" -> {

                        if (it is Button) {
                            calcPending.text = (str + it.text)
                        }

                    }
                    "operator" -> {
                        if (it is Button) {
                            calcPending.text = (str + it.text)
                        }
                    }

                    "equal" -> {
                        if (it is Button) {
                            try {
                                val expression = ExpressionBuilder(calcPending.text.toString()).build()
                                val result = expression.evaluate()
                                val longResult = result.toLong()

                                if (result == longResult.toDouble())
                                    resultFinal.text = longResult.toString()
                                else
                                    resultFinal.text = result.toString()
                            } catch (e:Exception) {

                            }
                        }
                    }

                    "clear" -> {
                        resultFinal.text = "0"
                        calcPending.text = ""
                    }
                }
            }
        }
    }

}
