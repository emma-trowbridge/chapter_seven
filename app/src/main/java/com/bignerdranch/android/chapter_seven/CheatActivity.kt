package com.bignerdranch.android.chapter_seven

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.chapter_seven.databinding.ActivityCheatBinding



class CheatActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityCheatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}