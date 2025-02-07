package com.mynotes.myapplication.navigation.navgraph

import androidx.compose.material3.Tab
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mynotes.myapplication.feature.favourites.presentation.FavouritesScreen
import com.mynotes.myapplication.feature.notes.presentation.AddEditNoteScreen
import com.mynotes.myapplication.navigation.Screen
import com.mynotes.myapplication.navigation.Tab

fun NavGraphBuilder.favourites(navController: NavController) {
    navigation(
        startDestination = Screen.FavouriteScreen.route,
        route = Tab.Favorites.route
    ){
        composable(
                route = Screen.FavouriteScreen.route
        ) {
            FavouritesScreen(
                navController = navController,
                onEditNoteClick = {noteId, noteTitle, noteDescription ->
                    navController.navigate("${Screen.AddEditNoteScreen.route}/$noteId/$noteTitle/$noteDescription")
                }
            )
        }
    }
}
