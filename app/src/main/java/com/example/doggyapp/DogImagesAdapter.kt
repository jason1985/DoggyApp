package com.example.doggyapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogImagesAdapter(private val dogImages: ArrayList<Dog>) : RecyclerView.Adapter<DogImagesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dogImage: ImageView = itemView.findViewById(R.id.iv_dog_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dog_image_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favOff: ImageView = holder.itemView.findViewById(R.id.iv_fav_off)
        val favOn: ImageView = holder.itemView.findViewById(R.id.iv_fav_on)

        // setup db
        val db = Room.databaseBuilder(
            holder.itemView.context.applicationContext,
            AppDatabase::class.java, "db"
        ).build()

        //check what favs exist
        CoroutineScope(IO).launch {
          val allFavs = db.favoriteDao().getAllFavorites()
            withContext(Main){
                if(allFavs.any{ it.url == dogImages[position].breed}){
                    favOff.visibility = View.INVISIBLE
                    favOn.visibility = View.VISIBLE
                } else{
                    favOff.visibility = View.VISIBLE
                    favOn.visibility = View.INVISIBLE
                }
            }
        }

//        Thread{
//            val favs = db.favoriteDao().getAllFavorites()
//            for(fav in favs){
//                Log.d("jason",fav.url)
//            }
//        }.start()

        holder.itemView.setOnClickListener{
            CoroutineScope(IO).launch {
                val allFavs = db.favoriteDao().getAllFavorites()
                if(allFavs.any{ it.url == dogImages[position].breed}){
                    // remove fav from db
                    db.favoriteDao().removeFavorite(dogImages[position].breed)

                    withContext(Main){
                        favOff.visibility = View.VISIBLE
                        favOn.visibility = View.INVISIBLE
                        Toast.makeText(holder.itemView.context,"Removed from Favorites",Toast.LENGTH_SHORT).show()
                    }
                } else{
                    // add fav to db
                    db.favoriteDao().addFavorite(Favorite(dogImages[position].breed))

                    withContext(Main){
                        favOff.visibility = View.INVISIBLE
                        favOn.visibility = View.VISIBLE
                        Toast.makeText(holder.itemView.context,"Added to Favorites",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        val dog = dogImages[position]
        Glide.with(holder.itemView.context)
            .load(dog.breed)
            .into(holder.dogImage)
    }

    override fun getItemCount(): Int {
        return dogImages.size
    }
}
