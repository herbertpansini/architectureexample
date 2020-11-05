package com.codinginflow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.noteRepository = new NoteRepository(application);
        allNotes = this.noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        this.noteRepository.insert(note);
    }

    public void update(Note note) {
        this.noteRepository.update(note);
    }

    public void delete(Note note) {
        this.noteRepository.delete(note);
    }

    public void deleteAllNotes() {
        this.noteRepository.deleteAllNotes();
    }
}
