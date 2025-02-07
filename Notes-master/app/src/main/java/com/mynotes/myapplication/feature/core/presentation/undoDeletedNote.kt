package com.mynotes.myapplication.feature.core.presentation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun undoDeletedNote(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    viewModel: MainViewModel
) {
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val result = snackbarHostState
            .showSnackbar(
                message = "Note Deleted ${viewModel.note.title}",
                actionLabel = "UNDO",
                duration = SnackbarDuration.Short
            )
        when(result) {
            SnackbarResult.ActionPerformed -> {
                viewModel.undoDeletedNote()
            }
            SnackbarResult.Dismissed -> {
                return@launch
            }
        }
    }
}