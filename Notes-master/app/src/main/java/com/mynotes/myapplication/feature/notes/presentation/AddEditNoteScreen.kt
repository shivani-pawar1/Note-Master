// AddEditNoteScreen.kt
package com.mynotes.myapplication.feature.notes.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mynotes.myapplication.feature.core.presentation.MainViewModel
import com.mynotes.myapplication.feature.core.presentation.toastMsg
import com.mynotes.myapplication.feature.core.ui.theme.poppinsFontFamily
import com.mynotes.myapplication.feature.core.ui.theme.ubuntuFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    noteId: Int,
    title: String,
    description: String,
    navController: NavController,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val focusRequest = FocusRequester()
    val textStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = poppinsFontFamily,
    )

    LaunchedEffect(noteId) {
        if (noteId > 0) {
            viewModel.getNoteById(noteId)
        } else {
            viewModel.updateNoteTitle(title)
            viewModel.updateNoteDescription(description)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = if (noteId > 0) "Edit Note" else "Add Note",
                        fontFamily = ubuntuFontFamily
                    )
                },
                modifier = Modifier,
                actions = {
                    IconToggleButton(
                        checked = viewModel.note.isBookmarked,
                        onCheckedChange = {
                            viewModel.updateIsBookmarked(it)
                            viewModel.updateNote(viewModel.note.copy(isBookmarked = it))
                        }
                    ) {
                        Icon(
                            imageVector = if (viewModel.note.isBookmarked) Icons.Filled.Bookmark else
                                Icons.Outlined.BookmarkAdd,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        if (viewModel.note.title.isNotBlank()) {
                            viewModel.insertNote(viewModel.note)
                            navController.popBackStack()
                        } else {
                            toastMsg(
                                context = context,
                                "Title can't be empty"
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            LaunchedEffect(
                true
            ) {
                if (noteId == -1) {
                    focusRequest.requestFocus()
                }
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .focusRequester(focusRequest),
                shape = RoundedCornerShape(3.dp),
                textStyle = textStyle,
                value = viewModel.note.title,
                onValueChange = {
                    viewModel.updateNoteTitle(it)
                },
                placeholder = {
                    Text(
                        text = "Add Title...",
                        style = textStyle
                    )
                }
            )

            viewModel.note.description?.let {
                TextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .focusRequester(focusRequest),
                    shape = RoundedCornerShape(3.dp),
                    textStyle = textStyle,
                    value = viewModel.note.description!!,
                    onValueChange = {
                        viewModel.updateNoteDescription(it)
                    },
                    placeholder = {
                        Text(
                            text = "Add Description...",
                            style = textStyle
                        )
                    }
                )
            }
        }
    }
}
