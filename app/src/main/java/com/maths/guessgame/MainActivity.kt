package com.maths.guessgame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.maths.guessgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var randomNumber = (1..20).random()
    private var count = 5

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.resetbtn.setOnClickListener {
            resetGame()
        }

        binding.guessnumber.setOnClickListener {
            val guessno = binding.guessInput.text.toString().toIntOrNull()

            if (guessno != null && guessno <= 20) {
                if (count > 0) {

                    when {
                        randomNumber > guessno -> { binding.feedback.text = "Too Low"
                            hintShow()
                        }
                        randomNumber < guessno -> { binding.feedback.text = "Too High"
                            hintShow()
                        }
                        else -> {
                            binding.feedback.text = "Congratulations!! You guessed the correct number"
                        }
                    }
                    count--
                    if (count >= 0) {
                        binding.live.text = "Chances Left: $count"
                    }
                }
                if (count == 0) {
                    binding.feedback.text = "Game Over! The correct number was $randomNumber"
                }
            } else {
                Toast.makeText(this, "Invalid Input or Out of Range", Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetGame() {
        randomNumber = (1..20).random()
        count = 5
        binding.guessInput.text.clear()
        binding.live.text = "Chances Left: $count"
        binding.feedback.text = " "
        binding.hint.text = " "
    }

    @SuppressLint("SetTextI18n")
    private fun hintShow() {
        val showHint = when {
            randomNumber % 2 == 0 -> "Even number"
            else -> "Odd number"
        }
        binding.hint.text = "Hint: $showHint"
    }
}