package com.mynotes.myapplication.feature.notes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mynotes.myapplication.feature.notes.presentation.components.getRandomColor

@Entity("note")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description : String? = null,
    val isBookmarked : Boolean = false
)
