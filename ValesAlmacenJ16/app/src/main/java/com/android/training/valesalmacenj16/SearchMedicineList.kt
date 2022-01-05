package com.android.training.valesalmacenj16

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchMedicineList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar : ActionBar? = getActionBar()
        setContentView(R.layout.activity_search_medicine_list)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}