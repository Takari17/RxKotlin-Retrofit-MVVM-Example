package com.example.rxkotlinretrofitexample.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxkotlinretrofitexample.R
import com.example.rxkotlinretrofitexample.data.JsonPlaceHolderSingleton
import com.example.rxkotlinretrofitexample.data.Repository
import com.example.rxkotlinretrofitexample.data.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onlyShowProgressBar()

        val repo = Repository(JsonPlaceHolderSingleton.api)
        val vmFactory = MainViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, vmFactory).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        compositeDisposable += viewModel.getAllUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { userDataList ->
                    initRecyclerView(userDataList)
                    onlyShowRecyclerView()
                },
                onError = { e -> onlyShowErrorMsg(e.message!!) }
            )
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    private fun onlyShowProgressBar() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
        errorMsg.visibility = View.INVISIBLE
    }


    private fun onlyShowRecyclerView() {
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
        errorMsg.visibility = View.INVISIBLE
    }


    private fun onlyShowErrorMsg(msg: String) {
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.INVISIBLE
        errorMsg.visibility = View.VISIBLE
        errorMsg.text = "Network Error: $msg"
    }

    private fun initRecyclerView(userDataList: List<UserData>) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UserDataAdapter(userDataList)
        }
    }
}