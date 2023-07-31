package com.example.appdepizza

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdepizza.databinding.ActivityThankYouScreenBinding

class ThankYouScreen : AppCompatActivity() {


    private lateinit var binding: ActivityThankYouScreenBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you_screen)


        window.statusBarColor = Color.parseColor("#E0E0E0")


    }
}