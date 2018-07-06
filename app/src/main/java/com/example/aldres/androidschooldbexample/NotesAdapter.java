package com.example.aldres.androidschooldbexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aldres on 29.06.2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    List<Note> noteList;

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        NotesViewHolder holder = new NotesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteBody.setText(note.getBody());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public NotesAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteBody;

        public NotesViewHolder(View noteView){
            super(noteView);
            noteTitle = noteView.findViewById(R.id.note_title);
            noteBody = noteView.findViewById(R.id.note_body);
        }
    }
}
