package com.example.doggyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_display)

        // floating action button - email

        val fab = findViewById<FloatingActionButton>(R.id.fab_favs_display)

        fab.setOnClickListener {
            var dialog = EmailDialog()
            dialog.show(supportFragmentManager, "EmailDialog")
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_favs_display)

        val db = Room.databaseBuilder(
            this.applicationContext,
            AppDatabase::class.java, "db"
        ).build()

        val dogImages = ArrayList<String>()

        CoroutineScope(Dispatchers.IO).launch {
            val favArr = db.favoriteDao().getAllFavorites()

            for(i in favArr){
                dogImages.add(i.url)
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesDisplay)
            adapter = FavoritesDisplayAdapter(dogImages)
        }
    }
}