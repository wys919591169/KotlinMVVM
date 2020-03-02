package com.itcunkou.kotlinmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.itcunkou.kotlinmvvm.DATABASE_NAME
import com.itcunkou.kotlinmvvm.db.bean.User
import com.itcunkou.kotlinmvvm.db.dao.UserDao

/**
 * @author wys
 * @date 2020/2/29/0029
 * Function:
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context : Context):AppDatabase{
            return instance ?: synchronized(this){
                instance?: buildAppDatabase(context).also { instance = it }
            }
        }

        private fun buildAppDatabase(context: Context):AppDatabase{
            return Room.databaseBuilder(context,AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<DBFirstConfig>().build()
                        WorkManager.getInstance().enqueue(request)
                    }
                })
                .build()
        }
    }
}