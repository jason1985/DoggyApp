package com.example.doggyapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun fetchAllBreeds(): Response<DogBreeds>

    @GET("breed/{selectedBreed}/images")
    suspend fun fetchAllImages(
        @Path(value = "selectedBreed", encoded = true) selectedBreed: String
    ): Response<DogBreeds>
}
