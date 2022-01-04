package com.android.training.toptendownloaderapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.loader.content.AsyncTaskLoader
import com.android.training.toptendownloaderapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private var binding = ActivityMainBinding.inflate(layoutInflater)
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.getRoot())
        Log.d(TAG, "onCreate called!")
        val downloadData = DownloadData()
        Log.d(TAG, "onCreate: done")
    }

    companion object{ //equivalent to static class
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"
            override fun doInBackground(vararg p0: String?): String {
                Log.d(TAG,"doInBackground called, starts with: ${p0[0]}")
                return "doInBackground completed"
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG,"onPostExecute called, parameter: $result")
            }
        }
    }
}