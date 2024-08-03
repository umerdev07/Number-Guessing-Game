package com.maths.guessgame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.maths.guessgame.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private val binding :ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
     private var randomNumber = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        randomNumber = (1..100).random()
         binding.guessnumber.setOnClickListener {
             val userGuess = binding.guessInput.text.toString().toIntOrNull()
             if(userGuess != null && userGuess <= 100){
                 when{
                     userGuess < randomNumber -> binding.feedback.text = "Too Low"
                     userGuess > randomNumber -> binding.feedback.text = "Too High"
                     else -> binding.feedback.text = "You guess the  correct number Congratulation!! $randomNumber"
                 }
             }else{
                 Toast.makeText(this, "Invalid Input Choose Correct Input in Range", Toast.LENGTH_SHORT).show()
             }
         }
    }
}