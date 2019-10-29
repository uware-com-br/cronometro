package br.com.uware.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    val time: Long = 1000000000L
    var timer = Timer(time)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStart.setOnClickListener {
            initTimer()
        }
    }
    private fun initTimer(){
        if(btnStart.text == "Start") start()
        else stop()
    }
    private fun start(){
        btnStart.text = "Stop"
        timer.start()
    }
    private fun stop(){
        btnStart.text = "Start"
        timer.cancel()
    }
    inner class Timer(miliis:Long) : CountDownTimer(miliis,1){
        var millisUntilFinished:Long = 0
        override fun onFinish() {
        }
        override fun onTick(millisUntilFinished: Long) {
            this.millisUntilFinished = millisUntilFinished
            val passTime = time - millisUntilFinished
            val f = DecimalFormat("00")
            val hour = passTime / 3600000 % 24
            val min = passTime / 60000 % 60
            val sec = passTime / 1000 % 60
            tvTime.text = f.format(hour) + ":" + f.format(min) + ":" + f.format(sec)
            val tic: Float = 100f/60f * sec
            progressBar.progress = tic.toInt()
        }
    }
}

