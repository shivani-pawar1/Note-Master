package com.mynotes.myapplication.feature.notes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mynotes.myapplication.feature.core.presentation.MainViewModel
import com.mynotes.myapplication.feature.core.ui.theme.poppinsFontFamily
import com.mynotes.myapplication.feature.notes.domain.model.Note
import kotlin.random.Random

@Composable
fun NoteCard(
    note: Note,
    onEditNoteClick:(Int, String,String) -> Unit,
    onUndoDeleteClick:() -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val backgroundColor = getRandomColor()
    val showDialog = remember { mutableStateOf(false) }
    val titleState = remember { mutableStateOf(note.title) }
    val descriptionState = remember { mutableStateOf(note.description ?: "") }
    val isBookmarkedState = remember { mutableStateOf(note.isBookmarked) }

    val textStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = poppinsFontFamily,
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                TextField(
                    value = titleState.value,
                    onValueChange = { titleState.value = it },
                    label = { Text("Title") },
                    textStyle = textStyle
                )
            },
            text = {
                LazyColumn {
                    item {
                        TextField(
                            modifier = Modifier.size(300.dp),
                            value = descriptionState.value,
                            onValueChange = { descriptionState.value = it },
                            label = { Text("Description") },
                            textStyle = textStyle,
                        )
                    }
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Checkbox(
                                checked = isBookmarkedState.value,
                                onCheckedChange = { isChecked ->
                                    isBookmarkedState.value = isChecked
                                }
                            )
                            Text(
                                text = "Bookmark",
                                fontFamily = poppinsFontFamily,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.updateNote(note.copy(
                        title = titleState.value,
                        description = descriptionState.value,
                        isBookmarked = isBookmarkedState.value
                    ))
                    showDialog.value = false
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .clickable { showDialog.value = true },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(12.dp)
        ) {
            if(!note.isBookmarked) {
                Spacer(modifier = Modifier.size(8.dp))
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = note.title,
                    modifier = Modifier
                        .weight(8.5f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = poppinsFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                if(note.isBookmarked) {
                    IconButton(
                        modifier = Modifier
                            .weight(1.5f),
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.BookmarkAdded ,
                            contentDescription = "Bookmark",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }
            if(!note.description.isNullOrBlank()) {
                Text(
                    text = note.description,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = if(note.description.isNullOrBlank()) 4.dp else 8.dp),
                color = MaterialTheme.colorScheme.secondary,
                thickness = 0.5.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        viewModel.deleteNote(note)
                        onUndoDeleteClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteSweep,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .size(20.dp).fillMaxWidth()
                    )
                }
            }
        }
    }
}

// List of beautiful colors for note backgrounds
val beautifulColors = listOf(
    Color(0xFFFFCDD2), // Red 100
    Color(0xFFF8BBD0), // Pink 100
    Color(0xFFE1BEE7), // Purple 100
    Color(0xFFD1C4E9), // Deep Purple 100
    Color(0xFFC5CAE9), // Indigo 100
    Color(0xFFBBDEFB), // Blue 100
    Color(0xFFB3E5FC), // Light Blue 100
    Color(0xFFB2EBF2), // Cyan 100
    Color(0xFFB2DFDB), // Teal 100
    Color(0xFFC8E6C9), // Green 100
    Color(0xFFDCEDC8), // Light Green 100
    Color(0xFFF0F4C3), // Lime 100
    Color(0xFFFFF9C4), // Yellow 100
    Color(0xFFFFECB3), // Amber 100
    Color(0xFFFFE0B2), // Orange 100
    Color(0xFFFFCCBC), // Deep Orange 100
    Color(0xFFD7CCC8), // Brown 100
    Color(0xFFCFD8DC)  // Blue Grey 100
)

// Function to get a random color from the list
fun getRandomColor(): Color {
    return beautifulColors[Random.nextInt(beautifulColors.size)]
}
