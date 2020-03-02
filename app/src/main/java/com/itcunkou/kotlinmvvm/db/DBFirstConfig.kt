package com.itcunkou.kotlinmvvm.db

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.itcunkou.kotlinmvvm.db.bean.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

/**
 * @author wys
 * @date 2020/2/29/0029
 * Function:
 */
class DBFirstConfig(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope(){
        val database = AppDatabase.getInstance(applicationContext)
        val mList = mutableListOf<User>()
        (1..888).forEach { mList.add(User(it, "吴$it")) }
        database.userDao().addList(mList)
        Result.success()
    }
}