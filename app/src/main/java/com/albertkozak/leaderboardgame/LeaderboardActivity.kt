package com.albertkozak.leaderboardgame

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_leaderboard.*

data class Score(val name: String, val score: Int)

class LeaderboardActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        leaderboard_list.layoutManager = LinearLayoutManager(this)

        val scores = listOf(
            Score("Albert Kozak",  250),
            Score("Ashley Stathis",  220),
            Score("Kasra Niktash",  100),
            Score("Athena Kozak",  240),
            Score("Tristan Peterson",  78),
            Score("Eduardo Fehr",  110),
            Score("Richard Lee",  50),
            Score("Harman Deol",  180)
        )
        leaderboard_list.adapter = LeaderboardAdapter(scores, this)
    }
}

private class LeaderboardAdapter(val scores: List<Score>, val context: Context): RecyclerView.Adapter<LeaderboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private class LeaderboardViewHolder(view: View): RecyclerView.ViewHolder(view)