package com.example.rxkotlinretrofitexample.ui.features.userdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxkotlinretrofitexample.data.Repository
import com.example.rxkotlinretrofitexample.data.remote.request.JsonPlaceHolderApi
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import io.reactivex.rxkotlin.subscribeBy


class UserDataViewModel : ViewModel() {

    private val repository = Repository(JsonPlaceHolderApi.invoke())
    private val userDataList = MutableLiveData<List<UserData>>()
    private val networkCallSuccess = MutableLiveData<Boolean>()

    fun executeNetworkCall() =
        repository.executeNetworkCall()
            .subscribeBy(
                onSuccess = { userData ->
                    userDataList.postValue(userData)
                    networkCallSuccess.value = true
                },
                onError = { networkCallSuccess.value = false })

    fun getNetworkCallSuccess(): LiveData<Boolean> = networkCallSuccess

    fun getNetworkCallResults(): LiveData<List<UserData>> = userDataList
}
