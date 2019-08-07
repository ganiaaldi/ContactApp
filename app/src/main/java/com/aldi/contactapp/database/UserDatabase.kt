package com.aldi.contactapp.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.aldi.contactapp.database.User
import com.aldi.contactapp.database.UserDao


@Database(entities = [User::class], version = 1)
abstract  class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {
        private var instance:UserDatabase? = null

        fun getInstance(context: Context): UserDatabase?{
            if (instance ==null){
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "user_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }

    }
    class PopulateDbAsyncTask(db: UserDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val userDao = db?.userDao()

        override fun doInBackground(vararg p0: Unit?) {
            userDao?.insert(User("Aldi", "aldi@gmail.com"))
            userDao?.insert(User("Bubu", "bu12101998@gmail.com"))
            userDao?.insert(User("Coco", "cocobit@gmail.com"))
        }
    }

}