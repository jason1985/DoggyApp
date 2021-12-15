package com.example.doggyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DogListAdapter(private val dogs: ArrayList<Dog>) : RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         val breed: TextView = itemView.findViewById(R.id.tv_breed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogs[position]
        holder.breed.text = dog.breed
    }

    override fun getItemCount(): Int {
        return dogs.size
    }
}
