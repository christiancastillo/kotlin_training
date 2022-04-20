package com.mundodedafne.apps.recyclerviewinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    // more info: https://handyopinion.com/how-to-show-vertical-list-in-kotlin-using-recyclerview-example/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview_layout)
        val data = arrayListOf<String>()
        for (i in 1..50){
            data.add("Item: ${i}")
        }

        val adapter = MyAdapter(this,data)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)
    }
}