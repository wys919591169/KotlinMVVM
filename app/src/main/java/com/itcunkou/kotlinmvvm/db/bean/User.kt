package com.itcunkou.kotlinmvvm.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author wys
 * @date 2020/2/29/0029
 * Function:
 */
@Entity
data class User(@PrimaryKey(autoGenerate = true)val id :Int,var name:String)