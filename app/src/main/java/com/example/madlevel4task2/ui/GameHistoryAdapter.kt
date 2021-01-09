package com.example.madlevel4task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.databinding.ItemGameBinding
import com.example.madlevel4task2.util.Constants

class GameHistoryAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemGameBinding.bind(itemView)

        fun databind(game: Game) {
            binding.tvDate.text = game.gameDate
            binding.tvMatchResult.text = game.gameResult

            when (game.computerChoice) {
                Constants.ROCK -> binding.ivComputerChoice.setImageResource(R.drawable.rock)
                Constants.PAPER -> binding.ivComputerChoice.setImageResource(R.drawable.paper)
                Constants.SCISSORS -> binding.ivComputerChoice.setImageResource(R.drawable.scissors)
            }
            when (game.personChoice) {
                Constants.ROCK -> binding.ivPersonChoice.setImageResource(R.drawable.rock)
                Constants.PAPER -> binding.ivPersonChoice.setImageResource(R.drawable.paper)
                Constants.SCISSORS -> binding.ivPersonChoice.setImageResource(R.drawable.scissors)
            }

        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return games.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }


}