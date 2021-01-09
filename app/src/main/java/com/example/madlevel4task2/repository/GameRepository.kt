package com.example.madlevel4task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.database.GameRoomDatabase
import com.example.madlevel4task2.model.Game

class GameRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    suspend fun getWinCount(): Int {
        return gameDao.getWinCount()
    }

    suspend fun getLoseCount(): Int {
        return gameDao.getLoseCount()
    }

    suspend fun getDrawCount(): Int {
        return gameDao.getDrawCount()
    }

}