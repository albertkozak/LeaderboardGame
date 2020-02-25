package com.albertkozak.leaderboardgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity: AppCompatActivity() {
    private val monsters: List<Monster> = listOf(
        Monster("Bobo", R.drawable.monster1_head, R.drawable.monster1_body, R.drawable.monster1_feet),
        Monster("BingBing", R.drawable.monster2_head, R.drawable.monster2_body, R.drawable.monster2_feet),
        Monster("Goopi", R.drawable.monster3_head, R.drawable.monster3_body, R.drawable.monster3_feet)
    )

    var score:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        shuffleMonsters()

        match_button.setOnClickListener {
            shuffleMonsters()
        }
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
            game_text.setText(monsters[0].name)
            score += 1000
            score_text.setText(score.toString())
        }
        else if(monster_head_image_view.getTag().toString() == R.drawable.monster2_head.toString() &&
            monster_body_image_view.getTag().toString() == R.drawable.monster2_body.toString() &&
            monster_feet_image_view.getTag().toString() == R.drawable.monster2_feet.toString())
        {
            game_text.setText(monsters[1].name)
            score += 1000
            score_text.setText(score.toString())
        }
        else if(monster_head_image_view.getTag().toString() == R.drawable.monster3_head.toString() &&
            monster_body_image_view.getTag().toString() == R.drawable.monster3_body.toString() &&
            monster_feet_image_view.getTag().toString() == R.drawable.monster3_feet.toString())
        {
            game_text.setText(monsters[2].name)
            score += 1000
            score_text.setText(score.toString())
        }
        else {game_text.setText("")}
    }
}

data class Monster(val name: String, val head: Int, val body: Int, val feet:Int)