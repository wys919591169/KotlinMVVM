package com.itcunkou.kotlinmvvm.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.itcunkou.kotlinmvvm.db.bean.User

/**
 * @author wys
 * @date 2020/2/29/0029
 * Function:
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getUserList():androidx.paging.DataSource.Factory<Int,User>

    @Insert
    fun add(user: User)

    @Insert
    fun addList(users:List<User>)

    @Delete
    fun del(user: User)
}