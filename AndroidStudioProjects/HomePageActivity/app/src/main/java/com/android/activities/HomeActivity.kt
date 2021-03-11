package com.android.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {
    lateinit var mRv:RecyclerView
    var mCountryList:ArrayList<String> = ArrayList<String>()
    var mAdapter:RvAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mRv = findViewById(R.id.id_rv)

        mCountryList.add("India")
        mCountryList.add("Pakistan")
        mCountryList.add("China")
        mCountryList.add("Japan")
        mRv.setLayoutManager(LinearLayoutManager(this))
        mAdapter = RvAdapter(this, mCountryList)
        mRv.setAdapter(mAdapter)


    }

    fun gotoDetails(){
            val myIntent = Intent(this@HomeActivity, DetailsActivity::class.java)
            startActivity(myIntent)
     }
}