package com.bignerdranch.android.chapter_seven

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.chapter_seven.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.chapter_seven.answer_is_true"
const val  EXTRA_IS_SHOWN = "com.bignerdranch.android.chapter_seven.answer_shown"
private const val KEY_IS_ANSWER_SHOWN = "com.bignerdranch.android.chapter_seven.answer_shown"

class CheatActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityCheatBinding
    private var answerIsTrue = false
    private var isAnswerShown = false //var to track if answer has been shown or not

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)


        //restores UI if existing:
        savedInstanceState?.let {
            isAnswerShown = it.getBoolean(KEY_IS_ANSWER_SHOWN, false)
            if (isAnswerShown) {
                showAnswer() //create fun to make this work
            }
        }

        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)

            setAnswerShownResult(true)
        }
    }
    //functions to show answer - displays answer when button is clicked/restored
    private fun showAnswer() {
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {

            putExtra(EXTRA_IS_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    //function - saves the state of UI for user
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_ANSWER_SHOWN, isAnswerShown)
    }


    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}

