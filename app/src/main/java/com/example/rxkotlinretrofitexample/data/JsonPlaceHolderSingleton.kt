package com.example.rxkotlinretrofitexample.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object JsonPlaceHolderSingleton {

    val api: JsonPlaceHolderApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonPlaceHolderApi::class.java)
}