package com.example.doggyapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DogListAdapter(private val dogs: ArrayList<Dog>) : RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

     class ViewHolder(itemView: View,dogs: ArrayList<Dog>) : RecyclerView.ViewHolder(itemView){
         init {
            itemView.setOnClickListener {
//                Log.d("RecyclerView", "onClick：${dogs[adapterPosition].breed}")
                val intent = Intent(itemView.context, GalleryActivity::class.java)
                intent.putExtra("selectedBreed", dogs[adapterPosition].breed)
                itemView.context.startActivity(intent)
            }
         }
         val breed: TextView = itemView.findViewById(R.id.tv_breed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return ViewHolder(view,dogs)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogs[position]
        holder.breed.text = dog.breed
    }

    override fun getItemCount(): Int {
        return dogs.size
    }
}
