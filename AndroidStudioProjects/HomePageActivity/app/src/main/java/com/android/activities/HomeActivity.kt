package com.android.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Response


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

        /*Create handle for the RetrofitInstance interface*/
        /*Create handle for the RetrofitInstance interface*/

        val service                                     = RetrofitClientInstance.getRetrofitInstance().create(GetDataService::class.java)
        val call: retrofit2.Call<List<RetroResponse>>   = service.getResponse

        call.enqueue(object : retrofit2.Callback<List<RetroResponse>> {

            override fun onResponse(call: retrofit2.Call<List<RetroResponse>>?, response: Response<List<RetroResponse>>?) {

                generateDataList(response?.body());
            }

            override fun onFailure(call: retrofit2.Call<List<RetroResponse>>?, t: Throwable?) {
//                progressDoalog.dismiss();
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.d("Error","error")
            }
        })



    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(response:List<RetroResponse>?) {
        var adapter:RvAdapter         =  RvAdapter(this, mCountryList)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@HomeActivity)
        mRv.setLayoutManager(layoutManager)
        mRv.setAdapter(adapter)
    }
}