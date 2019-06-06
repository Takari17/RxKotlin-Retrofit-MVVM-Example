package com.example.rxkotlinretrofitexample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.rxkotlinretrofitexample.R
import com.example.rxkotlinretrofitexample.ui.fragments.FailedFragment
import com.example.rxkotlinretrofitexample.ui.fragments.ProgressBarFragment
import com.example.rxkotlinretrofitexample.ui.fragments.RecyclerViewFragment
import com.example.rxkotlinretrofitexample.viewmodel.UserDataViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<UserDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // shows progress bar initially until network call result
        showProgressFragment()

        viewModel.getNetworkCallSuccess().observe(this, Observer { success ->
            if (success) showRecyclerViewFragment() else showFailedFragment()
        })

        viewModel.executeNetworkCall()
    }

    private fun showRecyclerViewFragment() =
        replaceContainer(RecyclerViewFragment())


    private fun showFailedFragment() =
        replaceContainer(FailedFragment())


    private fun showProgressFragment() =
        replaceContainer(ProgressBarFragment())


    private fun replaceContainer(fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
}