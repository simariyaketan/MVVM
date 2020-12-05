package com.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.R
import com.mvvm.adapter.UserRecyclerViewAdapter
import com.mvvm.database.UserRepository
import com.mvvm.database.UsersDatabase
import com.mvvm.databinding.ActivityMainBinding
import com.mvvm.model.Users
import com.mvvm.viewModel.UserViewModel
import com.mvvm.viewModel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = UsersDatabase.getInstance(application).subscriberDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.myViewModel = userViewModel

        binding.lifecycleOwner = this
        initView()

        userViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initView() {
        adapter = UserRecyclerViewAdapter({ selectedItem: Users -> listItemClicked(selectedItem) })
        binding.userRecyclerView.adapter = adapter
        displayUsersList()
    }

    private fun displayUsersList() {
        userViewModel.users.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(users: Users) {
        //Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        userViewModel.initUpdateAndDelete(users)
    }
}