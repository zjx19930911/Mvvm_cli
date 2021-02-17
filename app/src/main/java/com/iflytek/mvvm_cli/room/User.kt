package com.iflytek.mvvm_cli.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * 页面描述：User
 *
 * Created by jianxin on 2017/11/19.
 */

@Entity(tableName = "user")
class User(var name: String?) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var id: Int = 0
}