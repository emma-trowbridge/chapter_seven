package com.bignerdranch.android.chapter_seven

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val  CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle):ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var cheats = MutableList(questionBank.size) { false } //tracks cheats per question

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?: 0 //grabbed what it was coming into constructor
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value) //updated it with set value

    val currentQuestionAnswer:Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    //cheater for current question:
    fun isCheaterForCurrentQuestion(): Boolean {
        return cheats[currentIndex]
    }

    //updates status for current questions and takes value
    fun setCheaterForCurrentQuestion(value: Boolean) {
        cheats[currentIndex] = value
    }

    fun moveToNext() {

        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {

        currentIndex = if (currentIndex - 1 < 0) {
            questionBank.size - 1
        }else {
            (currentIndex - 1) % questionBank.size
        }
    }

    fun moveToNextText() {

        currentIndex = (currentIndex + 1) % questionBank.size
    }

}