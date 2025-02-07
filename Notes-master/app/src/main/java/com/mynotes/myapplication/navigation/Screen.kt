package com.mynotes.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.automirrored.outlined.StickyNote2
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object NoteScreen: Screen("note_screen")
    data object FavouriteScreen: Screen("favourite_screen")
    data object AddEditNoteScreen: Screen("add_edit_note-screen")
}

sealed class Tab(
    val route:String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String
) {
    data object Notes: Tab(
        route = "notes_tab",
        icon = BottomAppBarIcons.homeIconOutlined,
        label = "My Notes",
        selectedIcon = BottomAppBarIcons.homeIconFilled
    )

    data object Favorites: Tab(
        route = "favorites_tab",
        icon = BottomAppBarIcons.bookmarkIconOutlined,
        label = "Favourites",
        selectedIcon = BottomAppBarIcons.bookmarkIconFilled
    )
}

private object BottomAppBarIcons{
    val homeIconOutlined = Icons.AutoMirrored.Outlined.StickyNote2
    val homeIconFilled = Icons.AutoMirrored.Filled.StickyNote2

    val bookmarkIconOutlined = Icons.Outlined.Bookmarks
    val bookmarkIconFilled = Icons.Filled.Bookmarks
}

val bottomNavBarTabs = listOf(
        Tab.Notes,
        Tab.Favorites
    )