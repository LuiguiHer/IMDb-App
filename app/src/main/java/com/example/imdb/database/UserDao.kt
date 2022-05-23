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

    @Query("SELECT * FROM user_tab ORDER BY id ASC")
    fun readAllData(): List<User>

    @Query("DELETE FROM user_tab WHERE id=:id")
    fun deleteUser(id:Int)



}