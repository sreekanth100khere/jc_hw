package com.android.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class DetailsActivity : AppCompatActivity() {

    var mTitleTv:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var countryName: String? = intent.getStringExtra("countryName")

        mTitleTv         =   findViewById(R.id.id_details_title_tv)
        mTitleTv?.text   =   "Details :" + countryName



    }
}