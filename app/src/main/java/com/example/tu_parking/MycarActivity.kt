package com.example.tu_parking

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tu_parking.databinding.ActivityMycarBinding
import java.text.SimpleDateFormat
import java.util.Locale
import android.os.Handler
import android.os.Looper

class MycarActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private var updateRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMycarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DatabaseHelper(this)
        val carNum = intent.getStringExtra("car_num") ?: return

        val logs = dbHelper.getLogsByCarNum(carNum)
        if (logs.isNotEmpty()) {
            val latestLog = logs.last()
            val parkingId = "한국공대주차장"
            val availableSpots = dbHelper.getAvailableSpots(parkingId)

            binding.jare.text = "남은자리: $availableSpots/3"
            binding.Mynumber.text = "차량번호: ${latestLog.carNum}번"
            binding.seatinfo.text = "주차자리 1번"
            binding.Mytime.text = "주차한 시간: ${latestLog.eventTime}"

            val eventTimeMillis = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
                .parse(latestLog.eventTime)?.time ?: 0L

            updateRunnable = object : Runnable {
                override fun run() {
                    val totalMinutes = (getTime() - eventTimeMillis) / 1 //(1000 * 60)
                    val totalCost = (totalMinutes / 30) * 500
                    binding.Myprice.text = "주차 요금: $totalCost 원"

                    // 1초마다 반복 실행
                    handler.postDelayed(this, 1000)
                }
            }

            // 업데이트 시작
            updateRunnable?.let { handler.post(it) }

            updateParkingStatus(true)
            binding.parkingnum.text = latestLog.carNum
        } else {
            Toast.makeText(this, "등록된 차량이 없습니다.", Toast.LENGTH_SHORT).show()
            binding.Mynumber.text = "데이터 없음"
            binding.Mytime.text = "-"
            binding.Myprice.text = "-"
            updateParkingStatus(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 핸들러 정리 (메모리 누수 방지)
        updateRunnable?.let { handler.removeCallbacks(it) }
    }

    private fun updateParkingStatus(isParked: Boolean) {
        val parkingSpot1 = findViewById<LinearLayout>(R.id.parking_spot1)
        if (isParked) {
            parkingSpot1.setBackgroundColor(Color.RED)
        } else {
            parkingSpot1.setBackgroundColor(Color.GREEN)
        }
    }

    private fun getTime(): Long {
        return System.currentTimeMillis()
    }
}
