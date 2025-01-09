package com.example.tu_parking

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep


import androidx.appcompat.app.AppCompatActivity
import com.example.tu_parking.databinding.ActivityMainBinding // 바인딩


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val intent = Intent(applicationContext, SearchActivity::class.java)

        startActivity(intent)
        finish()
    }
}