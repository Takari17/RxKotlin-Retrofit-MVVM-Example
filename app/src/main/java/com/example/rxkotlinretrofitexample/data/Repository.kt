package com.example.rxkotlinretrofitexample.data

import com.example.rxkotlinretrofitexample.data.remote.api.JsonPlaceHolderApi
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class Repository(private val api: JsonPlaceHolderApi) {

    fun executeNetworkCall(): Single<List<UserData>> =
        api.getAllUserData()
            .subscribeOn(Schedulers.io())
}