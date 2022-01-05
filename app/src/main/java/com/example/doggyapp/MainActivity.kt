package com.example.doggyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggyapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        // retrofit

        val api = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.fetchAllBreedsAndSubBreeds()
                if(response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        handleData(response.body()!!)
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

    private fun handleData(breeds: BreedsAndSubBreeds){
        val obj = breeds.message
        val set = obj.keySet()
        val doggies = ArrayList<Dog>()

        for(i in set) {
            if(obj.getAsJsonArray(i).size() > 0) { // add all sub breeds to array
                val arr = obj.getAsJsonArray(i)
                for(j in arr){
                    if("${j.asString} $i".contains("shepherd australian")){
                        // this is one edge case in the sub breeds
                        // should read as "australian shepherd"
                        doggies.add(Dog("$i ${j.asString}"))
                    } else {
                        doggies.add(Dog("${j.asString} $i"))
                    }
                }
            } else {
                doggies.add(Dog(i))
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DogListAdapter(doggies)
        }

    }
}