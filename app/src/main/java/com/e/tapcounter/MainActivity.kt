package com.e.tapcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var counterTV: TextView
    private lateinit var counterButton: MaterialButton
    private lateinit var resetButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        init()

        var counter: Int = 0

        counterButton.setOnClickListener{


            counter++

            counterTV.text = counter.toString()
        }

        resetButton.setOnClickListener{
            counter = 0

            counterTV.text = counter.toString()
        }

    }

    fun init(){
        counterTV = findViewById(R.id.TV_counter)
        counterButton = findViewById(R.id.BTN_counter)
        resetButton = findViewById(R.id.BTN_reset)
    }
}