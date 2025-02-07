package com.mynotes.myapplication.feature.notes.domain.use_cases

import com.mynotes.myapplication.feature.notes.data.repository.NoteRepository
import com.mynotes.myapplication.feature.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotes @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke() : Flow<List<Note>> {
        return noteRepository.getAllNotes()
    }

}