package com.android.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CountryViewModel: ViewModel() {
    private  var mResponseList: MutableLiveData<List<RetroResponse>>? = null

    public fun getResponse():MutableLiveData<List<RetroResponse>>? {
        if(mResponseList == null){
            mResponseList = MutableLiveData<List<RetroResponse>>()

            loadResponse()

        }

        return mResponseList

    }

    //This method is using Retrofit to get the JSON data from URL
    private fun loadResponse() {
        val retrofit: Retrofit              =   RetrofitClientInstance.getRetrofitInstance()
        val api: GetDataService             =   retrofit.create(GetDataService::class.java)
        val call: Call<List<RetroResponse>> =   api.getResponse
        call.enqueue(object : Callback<List<RetroResponse>> {
            override fun onResponse(call: Call<List<RetroResponse>>?, response: Response<List<RetroResponse>>?) {
                mResponseList?.value = response?.body()

            }

            override fun onFailure(call: Call<List<RetroResponse>>?, t: Throwable?) {
            }
        })
    }
}