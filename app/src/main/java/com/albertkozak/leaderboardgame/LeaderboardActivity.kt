package com.albertkozak.leaderboardgame

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.item_score.view.*

data class Score(val name: String, val score: Int)

class LeaderboardActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        leaderboard_list.layoutManager = LinearLayoutManager(this)

        val scores = listOf(
            Score("1. Albert Kozak",  25000),
            Score("2. Ashley Stathis",  22000),
            Score("3. Kasra Niktash",  10000),
            Score("4. Athena Kozak",  24000),
            Score("5. Tristan Peterson",  7800),
            Score("6. Eduardo Fehr",  11000),
            Score("7. Richard Lee",  5000),
            Score("8. Harman Deol",  18000)
        )
        leaderboard_list.adapter = LeaderboardAdapter(scores, this)
    }
}

private class LeaderboardAdapter(val scores: List<Score>, val context: Context): RecyclerView.Adapter<LeaderboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        return LeaderboardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_score, parent, false))
    }

    override fun getItemCount(): Int {
        return scores.count()
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val score = scores[position]

        holder.itemView.item_name.text = score.name
        holder.itemView.item_score.text = score.score.toString()

    }
}

private class LeaderboardViewHolder(view: View): RecyclerView.ViewHolder(view)