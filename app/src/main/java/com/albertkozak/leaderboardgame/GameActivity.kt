package com.albertkozak.leaderboardgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_auth.view.*
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity: AppCompatActivity() {
    private lateinit var scoreDB: DatabaseReference
    private val monsters: List<Monster> = listOf(
        Monster("Bobo", R.drawable.monster1_head, R.drawable.monster1_body, R.drawable.monster1_feet),
        Monster("BingBing", R.drawable.monster2_head, R.drawable.monster2_body, R.drawable.monster2_feet),
        Monster("Goopi", R.drawable.monster3_head, R.drawable.monster3_body, R.drawable.monster3_feet)
    )

    var score:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        scoreDB = FirebaseDatabase.getInstance().getReference("userScore")

        shuffleMonsters()

        match_button.setOnClickListener {
            shuffleMonsters()
        }
        submit_button.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser?.email
            val userScore = score.toString()

            if (user != null && userScore != "0") {
                saveUserScore(user, userScore)
            }
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }
    }

    private fun saveUserScore(user: String, userScore: String) {
        val key = scoreDB.push().key
        key ?: return

        val userScore = UserScore(user, userScore)
        scoreDB.child(key).setValue(userScore)
    }

    private fun shuffleMonsters(){
        val theHead:Int=monsters.random().head
        monster_head_image_view.setImageResource(theHead)
        monster_head_image_view.setTag(theHead)
        val theBody:Int=monsters.random().body
        monster_body_image_view.setImageResource(theBody)
        monster_body_image_view.setTag(theBody)
        val theFeet:Int=monsters.random().feet
        monster_feet_image_view.setImageResource(theFeet)
        monster_feet_image_view.setTag(theFeet)

        if(monster_head_image_view.getTag().toString() == R.drawable.monster1_head.toString() &&
            monster_body_image_view.getTag().toString() == R.drawable.monster1_body.toString() &&
            monster_feet_image_view.getTag().toString() == R.drawable.monster1_feet.toString())
        {
            game_text.setText("You matched " + monsters[0].name + "! +1500pts")
            score += 1500
            score_text.setText(score.toString())
        }
        else if(monster_head_image_view.getTag().toString() == R.drawable.monster2_head.toString() &&
            monster_body_image_view.getTag().toString() == R.drawable.monster2_body.toString() &&
            monster_feet_image_view.getTag().toString() == R.drawable.monster2_feet.toString())
        {
            game_text.setText("You matched " + monsters[1].name + "! +1250pts")
            score += 1250
            score_text.setText(score.toString())
        }
        else if(monster_head_image_view.getTag().toString() == R.drawable.monster3_head.toString() &&
            monster_body_image_view.getTag().toString() == R.drawable.monster3_body.toString() &&
            monster_feet_image_view.getTag().toString() == R.drawable.monster3_feet.toString())
        {
            game_text.setText("You matched " + monsters[2].name + "! +1000pts")
            score += 1000
            score_text.setText(score.toString())
        }
        else {game_text.setText("")}
    }
}

data class Monster(val name: String, val head: Int, val body: Int, val feet:Int)
data class UserScore(
    var user: String = "",
    var userScore: String = ""
) : Comparable<UserScore> {
    override fun compareTo(other:UserScore): Int {
        if (this.userScore != other.userScore) {
            return this.userScore.toInt() - other.userScore.toInt()
        } else return 0
    }
}