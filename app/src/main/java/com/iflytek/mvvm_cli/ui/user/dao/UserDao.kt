package com.iflytek.mvvm_cli.ui.user.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iflytek.mvvm_cli.room.User
import io.reactivex.Single

/**
 * 页面描述：ArticleDao
 *
 * Created by ditclear on 2017/10/30.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetAll(users: List<User>)

    @Query("SELECT * FROM User WHERE userId= :id")
    suspend fun getUserById(id: Int): User

    @Query("SELECT * FROM User")
    suspend fun getAllUser(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

}