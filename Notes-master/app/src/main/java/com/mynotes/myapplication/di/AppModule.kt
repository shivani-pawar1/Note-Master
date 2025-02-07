package com.mynotes.myapplication.di

import android.content.Context
import androidx.room.Room
import com.mynotes.myapplication.feature.notes.data.LocalDatabase
import com.mynotes.myapplication.feature.notes.data.repository.NoteRepository
import com.mynotes.myapplication.feature.notes.data.repository.NoteRepositoryImpl
import com.mynotes.myapplication.feature.notes.domain.use_cases.DeleteNote
import com.mynotes.myapplication.feature.notes.domain.use_cases.GetAllNotes
import com.mynotes.myapplication.feature.notes.domain.use_cases.GetNoteById
import com.mynotes.myapplication.feature.notes.domain.use_cases.InsertNote
import com.mynotes.myapplication.feature.notes.domain.use_cases.UpdateNote
import com.mynotes.myapplication.feature.notes.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context)= Room
        .databaseBuilder(
            context,
            LocalDatabase::class.java,
            "local_database"
        ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: LocalDatabase) : NoteRepository {
        return NoteRepositoryImpl(db.noteDao())
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: NoteRepository) : UseCases {
        return UseCases(
            insertNote = InsertNote(repository),
            updateNote = UpdateNote(repository),
            deleteNote = DeleteNote(repository),
            getNoteById = GetNoteById(repository),
            getAllNotes = GetAllNotes(repository)
        )
    }
}