package com.android.training.toptendownloaderapp

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class ParseApplication {
    private val TAG = "ParseApplication"
    val application = ArrayList<FeedEntry>()

    fun parseXML(xmlData: String):Boolean{
        Log.d(TAG, "parse called with $xmlData")
        var status = true
        var inEntry = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.getEventType()
            var currentRecord = FeedEntry()
            while(eventType != XmlPullParser.END_DOCUMENT){
                val tagName = xpp.name.toLowerCase() //TODO: We should use the safe call operator ?
                when (eventType){
                    XmlPullParser.START_TAG -> {
                        Log.d(TAG, "parse: Starting tag for "+tagName)
                        if(tagName == "entry") {
                            inEntry = true
                        }
                    }
                    XmlPullParser.TEXT -> textValue = xpp.getText()
                    XmlPullParser.END_TAG -> {
                        Log.d(TAG,"parse: Ending tag for ${tagName}")
                        if(inEntry){
                            when(tagName){
                                "entry" -> {
                                    application.add(currentRecord)
                                    inEntry = false
                                    currentRecord = FeedEntry()
                                }
                                "name" -> {
                                    currentRecord.name = textValue
                                }
                                "artist" -> {
                                    currentRecord.artist = textValue
                                }
                                "releasedate" -> currentRecord.releaseDate = textValue
                                "summary" -> currentRecord.summary = textValue
                                "image" -> currentRecord.imageUrl = textValue
                            }
                        }
                    }
                }
                eventType = xpp.nextTag()
            }
            for (app in application){
                Log.d(TAG,"*******************")
                Log.d(TAG,app.toString())
            }
        } catch(e: Exception){
            e.printStackTrace()
            status = false
        }
        return status
    }
}