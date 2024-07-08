package com.myapp.viewmodetest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : ComponentActivity() {
    private lateinit var mCountIncreaseBtn:Button
    private lateinit var mCountIncreaseTv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        val mainActivityVMObj   =   ViewModelProvider(this).get(MainActivityVM::class.java)


        mCountIncreaseBtn       =   findViewById<Button>(R.id.id_increase_count_btn)!!
        mCountIncreaseTv        =   findViewById<TextView>(R.id.id_count_tv)!!

        mCountIncreaseTv.text   =   mainActivityVMObj.number.toString()


        mCountIncreaseBtn.setOnClickListener(View.OnClickListener {
            mainActivityVMObj.addNumber()
            var currentCount        =   mCountIncreaseTv.text.toString().toInt()
            var nextCount           =   currentCount +1
            mCountIncreaseTv.text   =   mainActivityVMObj.number.toString()
        })
    }
}
