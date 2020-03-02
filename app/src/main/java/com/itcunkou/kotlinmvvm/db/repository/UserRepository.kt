package com.itcunkou.kotlinmvvm.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.itcunkou.kotlinmvvm.db.bean.User
import com.itcunkou.kotlinmvvm.db.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author wys
 * @date 2020/2/29/0029
 * Function:
 */
class UserRepository (private val userDao:UserDao){
    fun getUserList() = userDao.getUserList().toLiveData(
        Config(
            pageSize = 30,
            enablePlaceholders = true)
    )
    //  可中断
    suspend fun addUser(name: String) {
        withContext(Dispatchers.IO) {
            val user = User(0, name)
            userDao.add(user)
        }
    }

    suspend fun delUser(id: Int) {
        withContext(Dispatchers.IO) {
            val user = User(id, "")
            userDao.del(user)
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance
                    ?: UserRepository(userDao).also { instance = it }
            }
    }
}