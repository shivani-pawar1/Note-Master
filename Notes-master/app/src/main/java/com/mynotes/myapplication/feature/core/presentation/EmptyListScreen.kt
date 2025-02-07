package com.mynotes.myapplication.feature.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.sp
import com.mynotes.myapplication.feature.core.ui.theme.ubuntuFontFamily

@Composable
fun EmptyListScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No notes found",
            fontFamily = ubuntuFontFamily,
            fontSize = 22.sp
        )
    }
}