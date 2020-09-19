package com.example.rxkotlinretrofitexample.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("users")
    fun getAllUserData(): Single<List<UserData>>
}