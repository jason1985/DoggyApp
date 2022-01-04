package com.example.doggyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggyapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowFavs.setOnClickListener {
            val intent = Intent(this, FavoritesDisplay::class.java)
            startActivity(intent)
        }

        ///retrofit

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchAllUsers().enqueue(object : Callback<DogBreeds> {
            override fun onResponse(call: Call<DogBreeds>, response: Response<DogBreeds>) {
//                d("test","onResponse: ${response.body()?.message!![0]}")
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<DogBreeds>, t: Throwable) {
                d("net", "onFailure $t")
            }
        })
    }

    private fun showData(breeds: DogBreeds){
            val dogs = ArrayList<Dog>()

            for (item in breeds.message) {
                dogs.add(Dog(item))
            }

            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = DogListAdapter(dogs)
            }
    }
}