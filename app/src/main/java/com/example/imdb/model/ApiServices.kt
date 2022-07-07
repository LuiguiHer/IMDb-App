package com.example.imdb.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {
    @GET
    suspend fun getMoviesHorizontal(@Url url:String):Response<ApiRespondBestMovies>

    @GET
    suspend fun getMovies(@Url url: String):Response<ApiRespondMovies>

    @GET
    suspend fun getMovieSearched(@Url url: String):Response<ApiRespondBestMovies>
}