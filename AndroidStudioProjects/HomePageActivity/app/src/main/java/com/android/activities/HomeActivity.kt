package com.android.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity() {
    lateinit var mRv:RecyclerView
    lateinit var  mEditText: EditText
    var mResponseList:ArrayList<RetroResponse>? = null
    var mSortSpinner:Spinner? = null
    var mFilteredList: ArrayList<RetroResponse> = ArrayList<RetroResponse>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mRv = findViewById(R.id.id_rv)

        supportActionBar?.hide()

        mSortSpinner = findViewById(R.id.id_sort_spinner) as Spinner

        val items: ArrayList<String> = ArrayList()

        items.add("A-Z")
        items.add("Z-A")

        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                applicationContext,
                android.R.layout.simple_spinner_dropdown_item,
                items
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mSortSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {

                //TODO: Use filtered list instead of mResponseList so that this will work with search result also.

                if(position == 0){
                    Toast.makeText(mEditText.context, "Sorted by A-Z", Toast.LENGTH_SHORT)
                        .show()
                    val sortByAscending = mResponseList?.sortedBy { RetroResponse -> RetroResponse.name
                    }

                    try {
                        var sortedAsc:ArrayList<RetroResponse> = ArrayList<RetroResponse>()
                        for( retroResponse in sortByAscending!!){
                            sortedAsc.add(retroResponse)

                        }
                        displayDataList(sortedAsc)

                    }catch (e: Exception){
                        Log.e("Exception", e.message.toString())
                    }


                }else if (position == 1){
                    Toast.makeText(mEditText.context, "Sorted by Z-A", Toast.LENGTH_SHORT)
                        .show()


                    val sortByDescending = mResponseList?.sortedByDescending { RetroResponse -> RetroResponse.name
                    }

                    try {
                        var sortedDes:ArrayList<RetroResponse> = ArrayList<RetroResponse>()
                        for( retroResponse in sortByDescending!!){
                            sortedDes.add(retroResponse)

                        }
                        displayDataList(sortedDes)

                    }catch (e: Exception){
                        Log.e("Exception", e.message.toString())

                    }


                }

            }
        }


            mSortSpinner?.setAdapter(adapter)
        mEditText = findViewById(R.id.id_search_et)


        mEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var mFilteredList: ArrayList<RetroResponse> = ArrayList<RetroResponse>()

                for (item in mResponseList!!) {
                    if (item.name?.contains(s.toString()) == true) {
                        mFilteredList.add(item)
                    }
                }

                //Now we have filtered list...

                displayDataList(mFilteredList)

                if (mFilteredList.size == 0) {
                    Toast.makeText(mEditText.context, "No Search Result Found", Toast.LENGTH_SHORT)
                            .show()
                }


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        /*Create handle for the RetrofitInstance interface*/
        /*Create handle for the RetrofitInstance interface*/



        val model: CountryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)

        model.getResponse()?.observe(this, Observer<List<RetroResponse?>?> { countryList ->

            mResponseList = countryList as ArrayList<RetroResponse>
            displayDataList(countryList as ArrayList<RetroResponse>?)
        })

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun displayDataList(response: ArrayList<RetroResponse>?) {

        var adapter:RvAdapter         = response?.let { RvAdapter(this, it) }!!
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@HomeActivity)
        mRv.setLayoutManager(layoutManager)
        mRv.setAdapter(adapter)
        mRv.adapter?.notifyDataSetChanged()
    }
}