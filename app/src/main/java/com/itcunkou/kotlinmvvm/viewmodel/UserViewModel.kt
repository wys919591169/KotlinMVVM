package com.itcunkou.kotlinmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.itcunkou.kotlinmvvm.db.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author wys
 * @date 2020/2/29/0029
 * Function:
 */
class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val userList = repository.getUserList()
    fun del(id:Int){
        GlobalScope.launch {
            repository.delUser(id)
        }
    }
}