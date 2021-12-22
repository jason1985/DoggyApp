package com.example.doggyapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    fun fetchAllUsers(): Call<DogBreeds>

    @GET("breed/{selectedBreed}/images")
    fun fetchAllImages(
        @Path(value = "selectedBreed", encoded = true) selectedBreed: String
    ): Call<DogBreeds>
}
