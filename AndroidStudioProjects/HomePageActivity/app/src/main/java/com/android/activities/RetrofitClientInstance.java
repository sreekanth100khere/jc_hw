package com.android.activities;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitClientInstance {
        public   static Retrofit  retrofit             = null;
        public  static String    BASE_URL             = "https://restcountries.eu";
        public static Retrofit  getRetrofitInstance(){
            if (retrofit == null) {
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            }
            return retrofit;
        }
}