package com.example.doggyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val selectedBreed: String = intent.getStringExtra("selectedBreed").toString()

        // actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Gallery of ${selectedBreed}s"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        // floating action button - email

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        fab.setOnClickListener {
            var dialog = EmailDialog()
            dialog.show(supportFragmentManager, "EmailDialog")
        }

        // retrofit

        val api = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.fetchAllImages(selectedBreed)
                if(response.isSuccessful) {
                    withContext(Main) {
                        showData(response.body()!!)
                    }
                }
            } catch (e: Throwable) {
                // handle failure
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showData(breeds: DogBreeds){
        val dogImages = ArrayList<Dog>()

        for (item in breeds.message) {
            dogImages.add(Dog(item))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_breed)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context.applicationContext,2)
            adapter = DogImagesAdapter(dogImages)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
