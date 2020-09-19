package com.example.rxkotlinretrofitexample.ui

import androidx.lifecycle.ViewModel
import com.example.rxkotlinretrofitexample.data.Repository


class MainViewModel(private val repository: Repository) : ViewModel() {

    fun getAllUserData() = repository.getAllUserData()
}