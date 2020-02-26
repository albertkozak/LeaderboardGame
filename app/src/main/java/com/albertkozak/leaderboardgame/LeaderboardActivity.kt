package com.albertkozak.leaderboardgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albertkozak.leaderboardgame.AuthActivity.Companion.AuthModeExtraName
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.item_score.view.*

data class Score(val name: String, val score: Int)

class LeaderboardActivity: AppCompatActivity() {
    private lateinit var scoreDB: DatabaseReference

    var scores: MutableList<UserScore> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        scoreDB = FirebaseDatabase.getInstance().getReference("userScore")
        scoreDB.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                scores = mutableListOf()
                dataSnapshot.children.forEach {
                    val score = it.getValue(UserScore::class.java)
                    if(score != null) {
                        scores.add(score)
                    }
                }
                update()
                scores.sortDescending()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        leaderboard_list.layoutManager = LinearLayoutManager(this)

        play_button.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser?.email
            if (user != null) {
                startActivity(Intent(this, GameActivity::class.java))
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }
    }

    private fun update(){
        leaderboard_list.adapter = LeaderboardAdapter(scores, this)
    }
}

private class LeaderboardAdapter(val scores: MutableList<UserScore>, val context: Context): RecyclerView.Adapter<LeaderboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        return LeaderboardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_score, parent, false))
    }

    override fun getItemCount(): Int {
        return scores.count()
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val score = scores[position]

        holder.itemView.item_name.text = score.user
        holder.itemView.item_score.text = score.userScore

    }
}

private class LeaderboardViewHolder(view: View): RecyclerView.ViewHolder(view)