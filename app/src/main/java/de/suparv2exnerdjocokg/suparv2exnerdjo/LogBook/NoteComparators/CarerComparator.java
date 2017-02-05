package de.suparv2exnerdjocokg.suparv2exnerdjo.LogBook.NoteComparators;

import java.util.Comparator;

import de.suparv2exnerdjocokg.suparv2exnerdjo.Todo.Note;

/**
 * Created by v2 on 05.02.2017.
 */

public class CarerComparator implements Comparator<Note> {
    @Override
    public int compare(Note note1, Note note2) {
        final int EQUAL = 0;

        int comparison = note1.getCarer().getName().compareTo(note2.getCarer().getName());
        if (comparison != EQUAL) return comparison;

        comparison = note1.getTimestamp().compareTo(note2.getTimestamp());
        if (comparison != EQUAL) return comparison;

        return EQUAL;
    }
}
