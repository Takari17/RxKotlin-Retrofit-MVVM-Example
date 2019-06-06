package com.example.rxkotlinretrofitexample.data.remote.response


data class UserData(
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

data class Company(
    val catchPhrase: String,
    val name: String
)