package com.example.rxkotlinretrofitexample.data

import com.example.rxkotlinretrofitexample.data.remote.request.JsonPlaceHolderApi
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class Repository(api: JsonPlaceHolderApi) {

    val getAllUserData: Single<List<UserData>> = api.getAllUserData()
        .subscribeOn(Schedulers.io())
}