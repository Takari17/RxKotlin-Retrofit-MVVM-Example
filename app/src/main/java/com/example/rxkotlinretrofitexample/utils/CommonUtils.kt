package com.example.rxkotlinretrofitexample.utils

import com.example.rxkotlinretrofitexample.data.remote.response.UserData

fun List<UserData>.getId(index: Int) = this[index].id.toString()

fun List<UserData>.getName(index: Int) = this[index].name

fun List<UserData>.getUserName(index: Int) = this[index].username

fun List<UserData>.getEmail(index: Int) = this[index].email

fun List<UserData>.getPhoneNumber(index: Int) = this[index].phone

fun List<UserData>.getWebsite(index: Int) = this[index].website

fun List<UserData>.getCompanyName(index: Int) = this[index].company.name

fun List<UserData>.getCatchPhrase(index: Int) = this[index].company.catchPhrase