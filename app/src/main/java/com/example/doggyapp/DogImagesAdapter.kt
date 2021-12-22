package com.example.doggyapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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

        if(MyApplication.getFavList().contains(dogImages[position].breed)) {
            favOff.visibility = View.INVISIBLE
            favOn.visibility = View.VISIBLE
        } else{
            favOff.visibility = View.VISIBLE
            favOn.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener{
            if(MyApplication.getFavList().contains(dogImages[position].breed)) {
                MyApplication.remove(dogImages[position].breed)
                favOff.visibility = View.VISIBLE
                favOn.visibility = View.INVISIBLE
            } else{
                MyApplication.add(dogImages[position].breed)
                favOff.visibility = View.INVISIBLE
                favOn.visibility = View.VISIBLE
            }
            Log.d("Jason",MyApplication.getFavList().toString())
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
