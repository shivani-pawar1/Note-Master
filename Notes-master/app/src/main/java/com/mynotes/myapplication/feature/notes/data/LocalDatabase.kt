package com.mynotes.myapplication.feature.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mynotes.myapplication.feature.notes.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = true)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}