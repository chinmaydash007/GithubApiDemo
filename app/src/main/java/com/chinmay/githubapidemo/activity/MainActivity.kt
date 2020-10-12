package com.chinmay.githubapidemo.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chinmay.githubapidemo.R
import com.chinmay.githubapidemo.databinding.MainActivityBinding
import com.chinmay.musicplayerclone.MusicPlayerActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button.setOnClickListener {
            val intent = Intent(this@MainActivity, GithubActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this@MainActivity, MusicPlayerActivity::class.java)
            startActivity(intent)
        }
    }
}