package com.android.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Response


class HomeActivity : AppCompatActivity() {
    lateinit var mRv:RecyclerView
    lateinit var  mEditText: EditText
    var mResponseList:ArrayList<RetroResponse>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mRv = findViewById(R.id.id_rv)

        mEditText = findViewById(R.id.id_search_et)


        mEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               var filteredList:ArrayList<RetroResponse>  = ArrayList<RetroResponse>()
                for (item in mResponseList!!){
                    if(item.name?.contains(s.toString()) == true){
                        filteredList.add(item)
                    }
                }

                //Now we have filtered list...

                displayDataList(filteredList)



            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        /*Create handle for the RetrofitInstance interface*/
        /*Create handle for the RetrofitInstance interface*/

        val service                                     = RetrofitClientInstance.getRetrofitInstance().create(GetDataService::class.java)
        val call: retrofit2.Call<List<RetroResponse>>   = service.getResponse

        call.enqueue(object : retrofit2.Callback<List<RetroResponse>> {

            override fun onResponse(call: retrofit2.Call<List<RetroResponse>>?, response: Response<List<RetroResponse>>?) {

                mResponseList = response?.body() as ArrayList<RetroResponse>

                displayDataList(response?.body() as ArrayList<RetroResponse>);


            }

            override fun onFailure(call: retrofit2.Call<List<RetroResponse>>?, t: Throwable?) {
//                progressDoalog.dismiss();
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.d("Error","error")
            }
        })



    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun displayDataList(response:ArrayList<RetroResponse>?) {


        var adapter:RvAdapter         = response?.let { RvAdapter(this, it) }!!
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@HomeActivity)
        mRv.setLayoutManager(layoutManager)
        mRv.setAdapter(adapter)
        mRv.adapter?.notifyDataSetChanged()
    }
}