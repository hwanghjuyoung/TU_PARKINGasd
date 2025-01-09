package com.example.tu_parking

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tu_parking.databinding.ActivityMycarBinding
import com.example.tu_parking.databinding.ActivitySearchBinding

class MycarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityMycarBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // DatabaseHelper 초기화
        val dbHelper = DatabaseHelper(this)

        // Intent로 전달된 car_num 가져오기
        val carNum = intent.getStringExtra("car_num") ?: return

        // 데이터베이스에서 차량 로그 가져오기
        val logs = dbHelper.getLogsByCarNum(carNum)

        // 가장 최근 로그 가져오기
        if (logs.isNotEmpty()) {
            val latestLog = logs.last() // 가장 최근 로그 (시간순 정렬 상태에서 마지막 데이터)

            // 텍스트뷰에 값 표시
            binding.Mynumber.text = "차량번호: "+latestLog.carNum + "번"
            //자리정보가 여기 들어가야함
            binding.Mytime.text = "주차한 시간:" + latestLog.eventTime
            binding.Myprice.text = "요금: 3000" // 이 부분은 계산 로직 추가 가능
        } else {
            // 로그가 없을 경우 처리 사실 이 경우는 안나옴
            Toast.makeText(this,"등록된 차량이 없습니다.", Toast.LENGTH_SHORT).show()
            binding.Mynumber.text = "데이터 없음"
            binding.Mytime.text = "-"
            binding.Myprice.text = "-"
        }






    }
}