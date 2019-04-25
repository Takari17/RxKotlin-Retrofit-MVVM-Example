package com.example.rxkotlinretrofitexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxkotlinretrofitexample.data.Repository
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy


class ViewModel : ViewModel() {

    private val repository = Repository()
    private val networkCallResults = MutableLiveData<List<UserData>>()
    private val networkCallSuccess = MutableLiveData<Boolean>()
    private lateinit var request: Disposable

    fun getNetworkCallSuccess(): LiveData<Boolean> = networkCallSuccess
    fun getNetworkCallResults(): LiveData<List<UserData>> = networkCallResults

    fun executeNetworkCall() {
        request = repository.executeNetworkCall()
            .subscribeBy(
                onSuccess = { userData ->
                    networkCallResults.value = userData
                    networkCallSuccess.value = true
                },
                onError = { networkCallSuccess.value = false })
    }

    override fun onCleared() {
        super.onCleared()
        request.dispose()
    }
}
