package com.example.rxkotlinretrofitexample.ui.features.userdata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxkotlinretrofitexample.R
import com.example.rxkotlinretrofitexample.data.remote.response.UserData
import com.example.rxkotlinretrofitexample.utils.*
import kotlinx.android.synthetic.main.recycler_view_layout.view.*


class UserDataAdapter(private val userData: List<UserData>, private val context: Context) :
    RecyclerView.Adapter<UserDataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = userData.size

    // Maybe I should of used Data Binding...
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.apply {
            id.text = getFormattedString(R.string.id, userData.getId(position))
            name.text = getFormattedString(R.string.name, userData.getName(position))
            userName.text = getFormattedString(R.string.user_name, userData.getUserName(position))
            email.text = getFormattedString(R.string.email, userData.getEmail(position))
            phone.text = getFormattedString(R.string.phone_num, userData.getPhoneNumber(position))
            website.text = getFormattedString(R.string.website, userData.getWebsite(position))
            companyName.text = getFormattedString(R.string.company_name, userData.getCompanyName(position))
            catchPhrase.text = getFormattedString(R.string.catch_phrase, userData.getCatchPhrase(position))
            catchPhrase.text = getFormattedString(R.string.catch_phrase, userData.getCatchPhrase(position))
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.id_view
        val name: TextView = itemView.name_view
        val userName: TextView = itemView.user_name_view
        val email: TextView = itemView.email_view
        val phone: TextView = itemView.phone_view
        val website: TextView = itemView.website_view
        val companyName: TextView = itemView.company_name_view
        val catchPhrase: TextView = itemView.catch_phrase_view
    }

    private fun getFormattedString(id: Int, inputText: String) =
        context.getString(id, inputText)
}