package com.example.madlevel4task2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madlevel4task2.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Query("SELECT COUNT(id) FROM games WHERE gameResult like '%win%'")
    suspend fun getWinCount(): Int

    @Query("SELECT COUNT(id) FROM games WHERE gameResult like '%lose%'")
    suspend fun getLoseCount(): Int

    @Query("SELECT COUNT(id) FROM games WHERE gameResult like '%draw%'")
    suspend fun getDrawCount(): Int

}