package com.example.rxkotlinretrofitexample.data.remote.request

import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JsonPlaceHolderApi {


    @GET(API)
    fun getAllUserData(): Single<List<UserData>>


    companion object {

        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val API = "users"

        private var api: JsonPlaceHolderApi? = null

        operator fun invoke(): JsonPlaceHolderApi {

            if (api == null) {
                api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JsonPlaceHolderApi::class.java)
            }
            return api!!
        }
    }
}