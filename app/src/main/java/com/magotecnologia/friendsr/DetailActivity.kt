package com.magotecnologia.friendsr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.magotecnologia.friendsr.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var friend: Friend
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra(FRIEND_EXTRA)) {
            friend = intent.getParcelableExtra<Friend>(FRIEND_EXTRA)
        }
        fillData()
        binding.rbScore.setOnRatingBarChangeListener { _, newScore, _ ->
            friend.score = newScore
            goBackToMain()
        }
    }

    fun fillData() {
        binding.rbScore.rating = friend.score
        binding.tvDescription.text = friend.description
        binding.ivFriendPhoto.load(friend.photo)
    }

    fun goBackToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(FRIEND_EXTRA, friend)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val DETAIL_CODE = 55
        const val FRIEND_EXTRA = "FRIEND"

    }
}
