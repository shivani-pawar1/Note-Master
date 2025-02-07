// AppNavigation.kt
package com.mynotes.myapplication.navigation.navgraph

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mynotes.myapplication.feature.core.presentation.MainViewModel
import com.mynotes.myapplication.feature.core.ui.theme.ubuntuFontFamily
import com.mynotes.myapplication.navigation.Screen
import com.mynotes.myapplication.navigation.Tab
import com.mynotes.myapplication.navigation.bottomNavBarTabs

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isBottomAppBarVisible = rememberSaveable(navBackStackEntry) {
        navBackStackEntry?.destination?.route == Screen.NoteScreen.route ||
                navBackStackEntry?.destination?.route == Screen.FavouriteScreen.route
    }

    Scaffold(
        bottomBar = {
            if (isBottomAppBarVisible) {
                MyBottomNavigation(navController = navController)
            }
        }
    ) { contentPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            navController = navController,
            startDestination = Tab.Notes.route
        ) {
            notes(navController = navController, viewModel = viewModel)
            favourites(navController = navController)
        }
    }
}


@Composable
private fun MyBottomNavigation(
    navController: NavController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavBarTabs.forEach { tab ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == tab.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    Log.d("Navigation", "Navigating to ${tab.route}")
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) tab.selectedIcon else tab.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        fontFamily = ubuntuFontFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}
