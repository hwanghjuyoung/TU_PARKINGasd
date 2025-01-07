package com.example.tu_parking

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tu_parking.databinding.ActivityMycarBinding
import com.example.tu_parking.databinding.ActivitySearchBinding

class MycarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val example = data(1, "A1", true, "1234", "Tu", "03:05:24")

        val binding = ActivityMycarBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.Mynumber.text = "${example.car_num}"
        binding.Mytime.text= "${example.event_time}"
        binding.Myprice.text = "3000"







    }
}