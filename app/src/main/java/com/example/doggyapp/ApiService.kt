package com.example.doggyapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    fun fetchAllUsers(): Call<DogBreeds>
}