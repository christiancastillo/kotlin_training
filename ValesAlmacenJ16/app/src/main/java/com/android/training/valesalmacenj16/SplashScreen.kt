package com.android.training.valesalmacenj16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class SplashScreen : AppCompatActivity() {

    private val SPLASH_DELAY : Long = 1000
    private var mDelayHandler : Handler? = null
    private var progressBarStatus = 0
    var dummy : Int = 0
    private lateinit var screenProgressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        screenProgressBar = findViewById(R.id.splash_screen_progress_bar)
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable,SPLASH_DELAY)
    }

    private fun launchMainActivity(){
        var intent = Intent(this,MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
        mDelayHandler!!.removeCallbacks(mRunnable)
    }

    private val mRunnable : Runnable = Runnable {
        Thread(Runnable{
            while (progressBarStatus < 100) {
                try {
                    dummy = dummy + 10
                    Thread.sleep(100)
                } catch(e: Exception){
                    e.printStackTrace()
                }
                progressBarStatus =  dummy
                screenProgressBar.setProgress(progressBarStatus)
            }
            launchMainActivity()
        }).start()
    }

    override fun onDestroy() {
        if(mDelayHandler != null){
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}