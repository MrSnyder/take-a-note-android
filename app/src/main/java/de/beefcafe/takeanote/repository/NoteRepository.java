package de.beefcafe.takeanote.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.beefcafe.takeanote.database.NoteDatabase;
import de.beefcafe.takeanote.entity.Note;

public class NoteRepository {

    private final NoteDao noteDao;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase instance = NoteDatabase.getInstance(application);
        noteDao = instance.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new AsyncTask<Note, Void, Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.insert(notes[0]);
                return null;
            }
        }.execute(note);
    }

    public void update(Note note) {
        new AsyncTask<Note, Void, Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.update(notes[0]);
                return null;
            }
        }.execute(note);
    }

    public void delete(Note note) {
        new AsyncTask<Note, Void, Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.delete(notes[0]);
                return null;
            }
        }.execute(note);
    }

    public void deleteAllNotes() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDao.deleteAll();
                return null;
            }
        }.execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
}
