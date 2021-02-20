package com.iflytek.mvvm_cli.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

import com.iflytek.mvvm_cli.ui.user.dao.UserDao


/**
 * 页面描述：AppDatabase
 *
 */
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app.db"
            )
                .addMigrations(mig_1_2)
                .build()

        var mig_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //数据库升级
//                database.execSQL("ALTER TABLE yyy" + " ADD COLUMN xxx CHARACTER");
            }

        }
    }

}