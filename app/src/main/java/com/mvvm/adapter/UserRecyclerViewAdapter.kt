package com.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.R
import com.mvvm.databinding.UserListItemBinding
import com.mvvm.model.Users

class UserRecyclerViewAdapter (private val clickListener:(Users)->Unit)
    : RecyclerView.Adapter<MyViewHolder>()
{
    private val subscribersList = ArrayList<Users>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : UserListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position],clickListener)
    }

    fun setList(users: List<Users>) {
        subscribersList.clear()
        subscribersList.addAll(users)

    }

}

class MyViewHolder(val binding: UserListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber: Users,clickListener:(Users)->Unit){
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener{
            clickListener(subscriber)
        }
    }
}