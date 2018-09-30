package com.akash.revealswitch

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.akash.RevealSwitch

class MainActivity : AppCompatActivity() {
    private lateinit var switch: RevealSwitch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switch = findViewById(R.id.revealSwitch)
//        switch.setAnimationDuration(500)
        switch.setEnabledTrackColor(Color.CYAN)
//        switch.setDisabledTrackColor(Color.GREEN)
        switch.setToggleListner(object : OnToggleListener {
            override fun onToggle(isEnable: Boolean) {
//                Toast.makeText(baseContext,"$isEnable",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
