package com.example.rxkotlinretrofitexample.data.remote.api

import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import io.reactivex.Single
import org.reactivestreams.Publisher
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val API = "users"

interface JsonPlaceHolderApi {

    @GET(API)
    fun getAllUserData(): Single<List<UserData>>


    companion object{
        operator fun invoke(): JsonPlaceHolderApi {

            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JsonPlaceHolderApi::class.java)
        }
    }
}