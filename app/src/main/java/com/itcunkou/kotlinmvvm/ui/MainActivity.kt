package com.itcunkou.kotlinmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.itcunkou.kotlinmvvm.R
import com.itcunkou.kotlinmvvm.adapter.UserAdapter
import com.itcunkou.kotlinmvvm.db.AppDatabase
import com.itcunkou.kotlinmvvm.db.repository.UserRepository
import com.itcunkou.kotlinmvvm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository =
                    UserRepository.getInstance(AppDatabase.getInstance(this@MainActivity).userDao())
                return UserViewModel(repository) as T
            }
        }).get(UserViewModel::class.java)
    }
    private val adapter = UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        adapter.setOnItemClick { user ->
            viewModel.del(user.id)
        }
        rv_test.adapter=adapter
//        LiveData
        viewModel.userList.observe(this, Observer { adapter.submitList(it) })
    }
}
