package com.example.tu_parking

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.appcompat.app.AppCompatActivity
import com.example.tu_parking.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySearchBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val example = data(1, "A1", true, "1234", "Tu", "03:05:24")

        var edit = binding.number.editableText

        binding.btn.setOnClickListener {
            if (edit.toString() == example.car_num) {
                val intent = Intent(applicationContext, MycarActivity::class.java)
                startActivity(intent)
            }
        }





    }
}