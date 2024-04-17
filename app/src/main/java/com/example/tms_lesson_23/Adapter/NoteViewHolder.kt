package com.example.tms_lesson_23.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_lesson_23.Model.Note
import com.example.tms_lesson_23.databinding.NoteItemBinding

class NoteViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note, important: Boolean, onLongClick: ((Note) -> Unit)?) {
        with(binding) {
            if (important) {
                noteItems.setBackgroundColor(Color.parseColor("#662e1c"))
                changeText(header, text, note)
            } else {
                changeText(header, text, note)
            }
        }

        itemView.setOnLongClickListener() {
            onLongClick?.invoke(note)
            true
        }
    }

    private fun changeText(header: TextView, text: TextView, note: Note) {
        header.text = note.header
        text.text = note.text
    }

    companion object {
        fun from(parent: ViewGroup): NoteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val noteView = NoteItemBinding.inflate(layoutInflater, parent, false)
            return NoteViewHolder(noteView)
        }
    }
}