package com.android.activities

import retrofit2.Call
import retrofit2.http.GET


interface GetDataService {
    @get:GET("/rest/v2/all")
    val getResponse: Call<List<RetroResponse>>


}