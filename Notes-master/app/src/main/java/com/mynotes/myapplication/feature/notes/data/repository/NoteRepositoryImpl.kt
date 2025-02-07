package com.mynotes.myapplication.feature.notes.data.repository

import com.mynotes.myapplication.feature.notes.data.NoteDao
import com.mynotes.myapplication.feature.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao : NoteDao
) : NoteRepository{
    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note {
        return dao.getNoteById(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
       return dao.getAllNotes()
    }
}