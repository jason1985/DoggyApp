package com.example.doggyapp.activities.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doggyapp.DogApi
import com.example.doggyapp.EmailDialog
import com.example.doggyapp.R
import com.example.doggyapp.models.Dog
import com.example.doggyapp.models.DogBreeds
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {
    @Inject
    @Named("provideDogApi")
    lateinit var api: DogApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val selectedBreed: String = intent.getStringExtra("selectedBreed").toString()

        // actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Gallery of ${selectedBreed}s"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        // floating action button -> opens EmailDialog
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        fab.setOnClickListener {
            var dialog = EmailDialog()
            dialog.show(supportFragmentManager, "EmailDialog")
        }

        GlobalScope.launch(Dispatchers.IO){
            try {
                // handle sub breeds
                //   if selectedBreed has a space then split on space and reverse it
                //   ex: endpoint for "russell terrier" is /terrier/russell
                //   one edge case is "australian shepherd" and it's endpoint is /australian/shepherd
                lateinit var response: Response<DogBreeds>
                if(selectedBreed.contains(" ")){
                    if(selectedBreed.contains("australian shepherd")){ // edge case
                        response = api.fetchAllImages("australian/shepherd")
                    } else {
                        val v: List<String> = selectedBreed.split(" ")
                        val newBreed = "${v[1]}/${v[0]}"
                        Log.d("jason",newBreed)
                        response = api.fetchAllImages(newBreed)
                    }
                } else {
                    response = api.fetchAllImages(selectedBreed)
                }
                if(response.isSuccessful) {
                    withContext(Main) {
                        handleData(response.body()!!)
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

    private fun handleData(breeds: DogBreeds){
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
