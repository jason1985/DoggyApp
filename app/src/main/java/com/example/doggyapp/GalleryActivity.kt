package com.example.doggyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val selectedBreed: String = intent.getStringExtra("selectedBreed").toString()
        val sBreed = findViewById<TextView>(R.id.textViewGallery)
        sBreed.text = "This screen will use https://dog.ceo/api/$selectedBreed/hound/images endpoint\n" +
                " to show pictures of $selectedBreed" + "s"
    }
}