package com.example.doggyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggyapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        val api = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.fetchAllBreeds()
                if(response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        showData(response.body()!!)
                    }
                }
            } catch(e: Throwable) {
                // handle failure
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
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