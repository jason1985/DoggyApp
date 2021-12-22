package com.example.doggyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val selectedBreed: String = intent.getStringExtra("selectedBreed").toString()
        val sBreed = findViewById<TextView>(R.id.textViewGallery)
        sBreed.text = selectedBreed + "s"

        ///retrofit

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchAllImages(selectedBreed).enqueue(object : Callback<DogBreeds> {
            override fun onResponse(call: Call<DogBreeds>, response: Response<DogBreeds>) {
//                d("test","onResponse: ${response.body()?.message!![0]}")
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<DogBreeds>, t: Throwable) {
                Log.d("net", "onFailure $t")
            }

        })
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
}
