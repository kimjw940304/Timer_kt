package com.example.timer_kotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ebanx.swipebtn.OnActiveListener
import com.ebanx.swipebtn.OnStateChangeListener
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

        /*I'm OK 슬라이드 버튼(gradle에 모듈 추가 코드 필요)*/
        binding.cancelBtn.setOnStateChangeListener(OnStateChangeListener {
            Toast.makeText(this@MainActivity, "I'm fine Thank you", Toast.LENGTH_SHORT).show()
            stopTimer()
        })

        binding.cancelBtn.setOnActiveListener(OnActiveListener {
            Toast.makeText(this@MainActivity, "I'm fine Thank you", Toast.LENGTH_SHORT).show()
        })
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
    /*타이머 정지 (I'm Ok 슬라이드 동작시)*/
    private fun stopTimer() {
        timer.cancel()
        Toast.makeText(this@MainActivity, "I'm fine Thank you", Toast.LENGTH_SHORT).show()
    }

    /*시간 업데이트 */
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