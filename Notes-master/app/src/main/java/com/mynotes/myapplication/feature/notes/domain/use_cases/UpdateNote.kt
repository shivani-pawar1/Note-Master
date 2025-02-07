package com.mynotes.myapplication.feature.notes.domain.use_cases

import com.mynotes.myapplication.feature.notes.data.repository.NoteRepository
import com.mynotes.myapplication.feature.notes.domain.model.Note
import javax.inject.Inject

class UpdateNote @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.updateNote(note)
    }

}