package com.example.rxkotlinretrofitexample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxkotlinretrofitexample.R
import com.example.rxkotlinretrofitexample.ui.fragments.FailedFragment
import com.example.rxkotlinretrofitexample.ui.fragments.ProgressBarFragment
import com.example.rxkotlinretrofitexample.ui.fragments.RecyclerViewFragment
import com.example.rxkotlinretrofitexample.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showProgressFragment() // shows progress bar initially until network call result

        val viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        viewModel.getNetworkCallSuccess().observe(this, Observer { success ->  // emits on network call result
            if(success) showRecyclerViewFragment() else showFailedFragment() })

        viewModel.executeNetworkCall()
    }

    private fun showRecyclerViewFragment() {
        val recyclerViewFragment = RecyclerViewFragment()
        replaceContainer(recyclerViewFragment)
    }

    private fun showFailedFragment() {
    val failedFragment = FailedFragment()
        replaceContainer(failedFragment)
    }

    private fun showProgressFragment() {
        val progressFragment = ProgressBarFragment()
        replaceContainer(progressFragment)
    }

    private fun replaceContainer(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}