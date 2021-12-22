package com.example.doggyapp

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
//        val dogUrl: TextView = itemView.findViewById(R.id.tv_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dog_image_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogImages[position]
        Glide.with(holder.itemView.context)
            .load(dog.breed)
            .into(holder.dogImage)
//        holder.dogUrl.text = dog.breed
    }

    override fun getItemCount(): Int {
        return dogImages.size
    }

}
