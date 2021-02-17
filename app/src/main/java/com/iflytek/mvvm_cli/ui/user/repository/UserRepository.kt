package com.iflytek.mvvm_cli.ui.user.repository

import com.iflytek.mvvm_cli.room.User
import com.iflytek.mvvm_cli.ui.home.api.HomeApi
import com.iflytek.mvvm_cli.ui.user.api.UserApi
import com.iflytek.mvvm_cli.ui.user.dao.UserDao

class UserRepository(private val mUserApi: UserApi, private val mUserDao: UserDao) {

    suspend fun detail() = mUserApi.detail()

    suspend fun userQuery() = mUserDao.getAllUser()

    suspend fun userInsert(user: User) = mUserDao.insertUser(user)
}