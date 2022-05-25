package com.example.imdb.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_tab")
    fun readAllData(): List<User>

    @Query("DELETE FROM user_tab WHERE email=:email")
    fun deleteUser(email:String)

    @Query("SELECT * FROM user_tab WHERE email=:email")
    fun getUserByEmail(email:String): User

    @Query("SELECT * FROM user_tab WHERE email=:email AND password=:password")
    fun getUserByEmailPass(email:String, password:String): User




}