package com.example.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(

    var computerChoice: String,
    var personChoice: String,
    var gameDate: String,
    var gameResult: String,

    // at bottom because it's easier to use the generated constructor, since null is nullable you
    // don't have to provide last parameter in constructor
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)