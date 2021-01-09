package com.example.madlevel4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repository.GameRepository
import com.example.madlevel4task2.util.Constants
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    lateinit var personChoice: String
    lateinit var computerChoice: String
    lateinit var gameResult: String

    private lateinit var gameRepository: GameRepository
    private var mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())

        ivRock.setOnClickListener {
            computerMakeRandomChoice()
            personChoice = Constants.ROCK
            saveGameToDatabase()
        }

        ivPaper.setOnClickListener {
            computerMakeRandomChoice()
            personChoice = Constants.PAPER
            saveGameToDatabase()
        }

        ivScissors.setOnClickListener {
            computerMakeRandomChoice()
            personChoice = Constants.SCISSORS
            saveGameToDatabase()
        }

        updateStatistics()
    }

    private fun computerMakeRandomChoice() {
        when ((1..3).random()) {
            1 -> computerChoice = Constants.ROCK
            2 -> computerChoice = Constants.PAPER
            3 -> computerChoice = Constants.SCISSORS
        }
    }

    private fun updateUI() {
        when (computerChoice) {
            Constants.ROCK -> {
                ivComputerChoice.setImageResource(R.drawable.rock)
            }
            Constants.PAPER -> {
                ivComputerChoice.setImageResource(R.drawable.paper)
            }
            Constants.SCISSORS -> {
                ivComputerChoice.setImageResource(R.drawable.scissors)
            }
        }

        when (personChoice) {
            Constants.ROCK -> {
                ivPersonChoice.setImageResource(R.drawable.rock)
            }
            Constants.PAPER -> {
                ivPersonChoice.setImageResource(R.drawable.paper)
            }
            Constants.SCISSORS -> {
                ivPersonChoice.setImageResource(R.drawable.scissors)
            }
        }

        gameResult = getMatchResult(computerChoice, personChoice)
        tvMatchResult.text = gameResult

        updateStatistics()
    }

    private fun getMatchResult(computerChoice: String, personChoice: String) : String {
        if (computerChoice == personChoice) {
            return getString(R.string.draw_result)
        }

        when (computerChoice) {
            Constants.ROCK -> {
                if (personChoice == Constants.PAPER) {
                    return getString(R.string.win_result)
                }
            }
            Constants.PAPER -> {
                if (personChoice == Constants.SCISSORS) {
                    return getString(R.string.win_result)
                }
            }
            Constants.SCISSORS -> {
                if (personChoice == Constants.ROCK) {
                    return getString(R.string.win_result)
                }
            }
        }

        return getString(R.string.lose_result)
    }

    private fun updateStatistics() {
        var wins = 0
        var draws = 0
        var losses = 0

        mainScope.launch {
            withContext(Dispatchers.IO) {
                wins = gameRepository.getWinCount()
                draws = gameRepository.getDrawCount()
                losses = gameRepository.getLoseCount()
            }
            tvWinInput.text = wins.toString()
            tvDrawInput.text = draws.toString()
            tvLoseInput.text = losses.toString()
        }

    }

    private fun saveGameToDatabase() {
        mainScope.launch {
            val game = Game(computerChoice, personChoice, Date().toString(), gameResult)
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }

        updateUI()
    }
}