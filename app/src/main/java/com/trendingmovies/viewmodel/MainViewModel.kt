package com.trendingmovies.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trendingmovies.data.model.MovieItem
import com.trendingmovies.data.network.MovieApiClient
import com.trendingmovies.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val apiService = MovieApiClient.service

    private lateinit var repository: MovieRepository

    var trendingMovies: List<MovieItem> by mutableStateOf(listOf())

    lateinit var clickedItem: MovieItem

    init {

        fetchTrendingMovies()
    }

    fun fetchTrendingMovies() {

        repository = MovieRepository(apiService)

        viewModelScope.launch {

            var response = repository.fetchTrendingMovies()

            when (response) {

                is MovieRepository.Result.Success -> {

                    Log.d("MainViewModel", "Success")

                    trendingMovies = response.movieList
                }

                is MovieRepository.Result.Failure -> {

                    Log.d("MainViewModel", "Failure")
                }
            }
        }
    }

    fun itemClicked(item: MovieItem) {

        clickedItem = item
    }
}