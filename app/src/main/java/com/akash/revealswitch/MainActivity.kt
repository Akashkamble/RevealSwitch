package com.akash.revealswitch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.akash.RevealSwitch

class MainActivity : AppCompatActivity() {
    private val TAG : String = "MainActivity"
    private lateinit var switch: RevealSwitch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switch = findViewById(R.id.revealSwitch)
        switch.setEnable(true)
        switch.setToggleListener(object : OnToggleListener {
            override fun onToggle(isEnable: Boolean) {
                Log.e(TAG,"isEnabled ? $isEnable")
            }
        })
    }
}
