package com.e.tapcounter

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var counterTV: TextView
    private lateinit var counterButton: MaterialButton
    private lateinit var holdCounterButton: MaterialButton
    private lateinit var resetButton: MaterialButton

    var timer: Timer? = null
    var countTask: TimerTask? = null

    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        init()


        tasker()



        // Tap counter
        counterButton.setOnClickListener{


            counter++

            counterTV.text = counter.toString()
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    fun tasker(){
        holdCounterButton.setOnLongClickListener {
            start()
            true
        }

        holdCounterButton.setOnTouchListener { p0, p1 ->
            if (p1?.action == MotionEvent.ACTION_UP) {
                timer?.cancel()
                timer = null
                countTask?.cancel()
                countTask = null
            }

            false
        }

        // Reset counter
        resetButton.setOnClickListener{
            counter = 0

            counterTV.text = counter.toString()
        }
    }

    fun start(){

        if(timer == null || countTask == null){

            timer = Timer()

            countTask = timerTask {
                runOnUiThread{
                    counter++
                    counterTV.text = counter.toString()
                }
            }
        }

        timer!!.schedule(countTask, 0, 100)

    }

    fun init(){
        counterTV = findViewById(R.id.TV_counter)
        counterButton = findViewById(R.id.BTN_counter)
        holdCounterButton = findViewById(R.id.BTN_hold_counter)
        resetButton = findViewById(R.id.BTN_reset)
    }
}