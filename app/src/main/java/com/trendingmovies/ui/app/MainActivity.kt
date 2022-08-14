package com.trendingmovies.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trendingmovies.ui.app.details.MovieDetails
import com.trendingmovies.ui.app.list.MainList
import com.trendingmovies.ui.theme.MVVMComposeTheme
import com.trendingmovies.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MVVMComposeTheme {
                NavHost(navController = navController, startDestination = "trendingMovieList") {
                    composable("trendingMovieList") {
                        Surface(color = MaterialTheme.colors.background) {
                            MainList(navController = navController, mainViewModel = mainViewModel)
                        }
                    }
                    composable("movieDetails") {
                        MovieDetails(mainViewModel.clickedItem)
                    }
                }
            }
        }
    }
}