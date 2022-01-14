package com.android.training.toptendownloaderapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.loader.content.AsyncTaskLoader
import com.android.training.toptendownloaderapp.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class FeedEntry {
    var name:String = ""
    var artist: String = ""
    var releaseDate:String=""
    var summary:String=""
    var imageUrl:String=""

    override fun toString(): String {
        return """
            name = $name
            artist = $artist
            releaseDate = $releaseDate
            imageURL = $imageUrl
        """.trimIndent()
    }
}


class MainActivity : AppCompatActivity() {
    //private var binding = ActivityMainBinding.inflate(layoutInflater)
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        //var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        //setContentView(binding.getRoot())
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate called!")
        val downloadData = DownloadData()
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        Log.d(TAG, "onCreate: done")
    }

    companion object{ //equivalent to static class
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"
            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG,"doInBackground called, starts with: ${url[0]}")
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()){
                    Log.e(TAG,"doInBackground: Error downloading") //Log.e = es para errores, Log.d = para debug
                }
                return rssFeed
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                Log.d(TAG,"onPostExecute called, parameter: $result")
                val parseApplication = ParseApplication()
                parseApplication.parseXML(result)
            }

            private fun downloadXML(urlPath: String?):String{
                return URL(urlPath).readText() //this is called extended functions
//                val xmlResult = StringBuilder()
//                try {
//                    val url = URL(urlPath)
//                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
//                    val response = connection.responseCode
//                    Log.d(TAG, "downloadXML: The response was: $response")
//                    //val inputStream = connection.inputStream
//                    //val inputStreamReader = InputStreamReader(inputStream)
//                    //val reader = BufferedReader(inputStreamReader)
//
////                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
////                    val inputBuffer = CharArray(500)
////                    var charsRead = 0
////                    while (charsRead <= 0) {
////                        charsRead = reader.read(inputBuffer)
////                        if (charsRead > 0) {
////                            xmlResult.append(String(inputBuffer, 0, charsRead))
////                        }
////                    }
////                    reader.close()
//                    val stream = connection.inputStream
//                    stream.buffered().reader().use {
//                        reader -> xmlResult.append(reader.readText())
//                    }
//
//                    Log.d(TAG, "downloadXML: Received ${xmlResult.length} bytes")
//                    return xmlResult.toString()
//                }
//                catch(e: Exception) { //Managing exception in kotlin style
//                    val errorMessage: String
//                    when (e) {
//                        is MalformedURLException -> errorMessage = "Error Invalid URL, ${e.message}"
//                        is IOException -> "downloadXML: Error, IOException: ${e.message}"
//                        is SecurityException -> "downloadXML: Error, SecurityException ${e.message}"
//                     //   is Exception -> "downloadXML: Error, Unknown error: ${e.message}"
//                        else -> "Unknown error: ${e.message}"
//                    }
//                }
////                } catch (e: MalformedURLException){
////                    Log.e(TAG,"downloadXML: Error Invalid URL, ${e.message}")
////                } catch (e: IOException){
////                    Log.e(TAG,"downloadXML: Error, IOException: ${e.message}")
////                } catch(e: SecurityException){
////                    Log.e(TAG,"downloadXML: Error, SecurityException ${e.message}")
////                } catch(e: Exception){
////                    Log.e(TAG,"downloadXML: Error, Unknown error: ${e.message}")
////                }
//                return "" //if it gets here, there's been a problem, Return an empty string
            }
        }
    }
}