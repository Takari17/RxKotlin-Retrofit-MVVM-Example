package com.example.rxkotlinretrofitexample.data

import com.example.rxkotlinretrofitexample.data.remote.api.JsonPlaceHolderApi
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class Repository {

    private val api = JsonPlaceHolderApi.invoke()

    fun executeNetworkCall(): Single<List<UserData>> {
        return api.getAllUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}