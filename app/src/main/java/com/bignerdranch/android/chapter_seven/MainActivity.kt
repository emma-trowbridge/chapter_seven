package com.bignerdranch.android.chapter_seven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.bignerdranch.android.chapter_seven.databinding.ActivityMainBinding

private const val Tag = "MainActivity"

class MainActivity : AppCompatActivity() {

    //private lateinit var trueButton: Button
    //private lateinit var falseButton: Button
    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    /*private  val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)

    )//created list of questions...
        //from Question.kt - looks for integer (R.string.question.()), and then boolean (true or false)*/

    //private var answers = arrayOfNulls<Boolean>(questionBank.size) //tracks answer of each question, null = unanswered

    private var correctAnswerCount = 0

    //private var currentIndex = 0 //always start with 0, first value of index is "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        Log.d(Tag, "onCreate(Bundle) called")
        Log.d(Tag, "Got a QuizViewModel: $quizViewModel")

        //trueButton = findViewById(R.id.true_button)
        //falseButton = findViewById(R.id.false_button)

        binding.trueButton.setOnClickListener {
            /*Toast.makeText(
                this,
                R.string.correct,
                Toast.LENGTH_SHORT
            )
                .show() */

            checkAnswer(userAnswer = true)
        }

        binding.falseButton.setOnClickListener {
            /*Toast.makeText(
                this,
                R.string.incorrect,
                Toast.LENGTH_SHORT
            )
                .show()*/

            checkAnswer(userAnswer = false)
            }

        binding.previousButton.setOnClickListener{
            /*currentIndex = if (currentIndex - 1 < 0) {
                questionBank.size - 1 //wraps around to the last question
            }else {
                (currentIndex - 1) % questionBank.size
            }*/

            quizViewModel.moveToPrevious()

            updateQuestion()
        }

        binding.nextButton.setOnClickListener {
            //currentIndex = (currentIndex + 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)

            quizViewModel.moveToNext()

            updateQuestion()
        }

        binding.questionTextView.setOnClickListener{
            //currentIndex = (currentIndex + 1) % questionBank.size

            quizViewModel.moveToNextText()

            updateQuestion()
        }

        /*binding.resetButton.setOnClickListener {
            resetQuiz()
        }*/

        //got rid of enableAnswerButtons() from next, previous, and question buttons

        //var questionTextResId = questionBank[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)
        updateQuestion()
        }

        //logs:

        override fun onStart(){
            super.onStart()
            Log.d(Tag, "onStart() called")

        }

        override fun onResume() {
        super.onResume()
        Log.d(Tag, "onResume() called")
        }

        override fun onPause(){
            super.onPause()
            Log.d(Tag, "onPause() called")
        }

        override fun onStop(){
            super.onStop()
            Log.d(Tag, "onStop() called")
        }

        override fun onDestroy(){
            super.onDestroy()
            Log.d(Tag, "onDestroy() called")
        }


        private fun updateQuestion(){
            //val questionTextResId = questionBank[currentIndex].textResId
            val questionTextResId = quizViewModel.currentQuestionText

            binding.questionTextView.setText(questionTextResId)

            /*val answered = answers[currentIndex]
            if (answered != null) {
                disableAnswerButtons() //if answered, disabled buttons
            } else {
                enableAnswerButtons() //else enable the buttons so the user answers the question
            }*/
        }

        private fun checkAnswer(userAnswer:Boolean){
            //val correctAnswer = questionBank[currentIndex].answer //grabbing second quality
            val correctAnswer = quizViewModel.currentQuestionAnswer

            val messageResID = if (userAnswer == correctAnswer){
                correctAnswerCount++ //increments amount to counter
                R.string.correct
            } else{
                R.string.incorrect
            }

            Toast.makeText(
                this,
                messageResID,
                Toast.LENGTH_SHORT)
                .show()

            /*answers[currentIndex] = userAnswer == correctAnswer //saves the users answer, boolean = true/false
            disableAnswerButtons() // disables both buttons after user answers

            if (currentIndex == questionBank.size - 1) {
                computeUserScore() //checks to see if last question has been answered, if so it computes user's score after last question
            }*/
        }

        private fun disableAnswerButtons() {
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }

        private  fun enableAnswerButtons() {
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
        }

        /*private fun computeUserScore() {
            val score = (correctAnswerCount.toDouble() / questionBank.size) * 100
            val scoreMessage = String.format("Your score is: %.1f%%", score) //%.1f formats to 1 dec place, %% adds %
            Toast.makeText(
                this,
                scoreMessage,
                Toast.LENGTH_LONG,
            )
                .show()
        }*/
        /*private fun resetQuiz() {
            correctAnswerCount = 0 //reset correct answer counter
            currentIndex = 0 //reset question index
            answers = arrayOfNulls(questionBank.size) //resets answers to null (unanswered)

            enableAnswerButtons()

            updateQuestion()

            Toast.makeText(
                this,
                "Quiz has been reset. Try again !",
                Toast.LENGTH_LONG
            )
                .show()
        }*/
    }
