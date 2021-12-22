package com.example.doggyapp

import android.app.Application
import com.example.doggyapp.MyApplication
import java.util.ArrayList

class MyApplication : Application() {
    companion object {
        private var favList: MutableList<String> = ArrayList()
        fun getFavList(): List<String> {
            return favList
        }

        fun add(fav: String){
            favList.add(fav)
        }

        fun remove(fav: String){
            val temp: MutableCollection<String> = mutableListOf<String>()

            favList.filterTo(temp, { it != fav })
            favList = temp as MutableList<String>
        }
    }
}