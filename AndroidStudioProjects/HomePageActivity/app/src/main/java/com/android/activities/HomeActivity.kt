package com.android.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    lateinit var mEmptyView:View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mEmptyView = findViewById(R.id.id_empty_view)
        mEmptyView.setOnClickListener(View.OnClickListener {
            gotoDetails()
        })
    }

    fun gotoDetails(){
            val myIntent = Intent(this@HomeActivity, DetailsActivity::class.java)
            startActivity(myIntent)
     }
}