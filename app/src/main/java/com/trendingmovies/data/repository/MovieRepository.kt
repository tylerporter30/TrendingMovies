package com.trendingmovies.data.repository

import android.util.Log
import com.trendingmovies.data.model.MovieItem
import com.trendingmovies.data.network.MovieFetchApi

class MovieRepository(val movieFetchApi: MovieFetchApi) {

    sealed class Result {

        object LOADING : Result()
        data class Success(val movieList: List<MovieItem>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    suspend fun fetchTrendingMovies(): Result {

        return try {

            val movieList = movieFetchApi.fetchTrendingMovieList().results
            Log.d("MovieList", "Success" + movieList.size)
            Result.Success(movieList = movieList)

        } catch (exception: Exception) {

            Log.d("MovieList", "Failure")
            Result.Failure(exception)
        }
    }
}