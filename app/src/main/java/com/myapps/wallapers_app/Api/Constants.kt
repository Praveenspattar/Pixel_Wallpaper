package com.myapps.wallapers_app.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Constants {
    const val BASE_URL = "https://api.pexels.com/v1/"


    fun getRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api by lazy {
        getRetrofitInstance().create(ApiInterface::class.java)
    }
}