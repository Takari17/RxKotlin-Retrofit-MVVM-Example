package com.example.rxkotlinretrofitexample.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxkotlinretrofitexample.R
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import com.example.rxkotlinretrofitexample.ui.MyAdapter
import com.example.rxkotlinretrofitexample.viewmodel.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recycler_view.*


class RecyclerViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(activity!!).get(ViewModel::class.java)

        viewModel.getNetworkCallResults().observe(this, Observer { userData -> initRecyclerView(userData) })
    }

    private fun initRecyclerView(userData: List<UserData>) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MyAdapter(userData, context)
        }
    }
}