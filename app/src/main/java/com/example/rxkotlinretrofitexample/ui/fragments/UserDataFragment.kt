package com.example.rxkotlinretrofitexample.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxkotlinretrofitexample.R
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import com.example.rxkotlinretrofitexample.ui.UserDataAdapter
import com.example.rxkotlinretrofitexample.viewmodel.UserDataViewModel
import kotlinx.android.synthetic.main.fragment_recycler_view.*


class UserDataFragment : Fragment() {

    private val viewModel by activityViewModels<UserDataViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNetworkCallResults().observe(this, Observer { userData -> initRecyclerView(userData) })
    }

    private fun initRecyclerView(userData: List<UserData>) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = UserDataAdapter(userData, context)
        }
    }
}