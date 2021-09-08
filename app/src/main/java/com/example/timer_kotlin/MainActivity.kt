package com.example.timer_kotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.timer_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var timer : CountDownTimer
    private var tempTime:Long=0

    private var mBinding: ActivityMainBinding?=null
    private val binding get() =mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*알람 울리면 바로 타이머 스타트*/
        startTimer()


    }
    /*타이머 메소드*/
    private fun startTimer(){

        timer = object : CountDownTimer(30 * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                tempTime= millisUntilFinished
                updateTimer()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Send SOS Message", Toast.LENGTH_SHORT).show()

                /*카카오톡 메시지 발송하는 액티비티로 넘어가는 코드 추가*/
            }
        }
        timer.start()
    }
    /*시간 업데이트 view*/
    private fun updateTimer() {
        val minutes = tempTime.toInt() / 60000
        val seconds = tempTime.toInt() % 60000 / 1000
        var timeLeftText = ""
        if (minutes < 10) timeLeftText += "0"
        timeLeftText += "$minutes:"
        if (seconds < 10) timeLeftText += "0"
        timeLeftText += seconds
        binding.countdownText.setText(timeLeftText)

    }


}