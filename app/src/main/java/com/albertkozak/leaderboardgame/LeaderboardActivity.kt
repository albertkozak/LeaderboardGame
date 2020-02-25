package com.albertkozak.leaderboardgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albertkozak.leaderboardgame.AuthActivity.Companion.AuthModeExtraName
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.item_score.view.*

data class Score(val name: String, val score: Int)

class LeaderboardActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        play_button.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser?.email
            if (user != null) {
                startActivity(Intent(this, GameActivity::class.java))
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }

        leaderboard_list.layoutManager = LinearLayoutManager(this)

        val scores = listOf(
            Score("1. Albert Kozak",  25000),
            Score("2. Ashley Stathis",  22000),
            Score("3. Kasra Niktash",  10000),
            Score("4. Athena Kozak",  24000),
            Score("5. Tristan Peterson",  7800),
            Score("6. Eduardo Fehr",  11000),
            Score("7. Richard Lee",  5000),
            Score("8. Harman Deol",  18000),
            Score("9. Christian Fletcher",  21000),
            Score("10. Franco Pontaletta",  19000),
            Score("11. Cameron Jones",  14000),
            Score("12. Elizabeth Stathis",  14500),
            Score("13. Stan Stathis",  8000),
            Score("14. Kate Draken",  16000),
            Score("15. Max Draken",  15000),
            Score("16. Edmund Mu",  19000)
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