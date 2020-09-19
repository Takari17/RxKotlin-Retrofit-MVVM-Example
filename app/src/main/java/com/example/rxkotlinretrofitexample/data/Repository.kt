package com.example.rxkotlinretrofitexample.data


class Repository(private val api: JsonPlaceHolderApi) {

    fun getAllUserData() = api.getAllUserData()
}