package com.example.tms_lesson_23.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tms_lesson_23.Model.Note
import com.example.tms_lesson_23.Model.SingletonList

class MainViewModel : ViewModel() {

    private val _notes = MutableLiveData<MutableList<Note>>()
    val notes : LiveData<List<Note>> = _notes.map { item -> item?.map { it } ?: emptyList() }

    private val _header : MutableLiveData<String> = MutableLiveData()
    val header : LiveData<String>
        get() = _header

    private val _text : MutableLiveData<String> = MutableLiveData()
    val text : LiveData<String>
        get() = _text

    private val _isImportant : MutableLiveData<Boolean> = MutableLiveData()
    val isImportant : LiveData<Boolean>
        get() = _isImportant

    fun onHeaderChanged(header : String) {
        _header.value = header
    }

    fun onTextChanged(text : String) {
        _text.value = text
    }

    fun onCheckBoxClicked(isImportant : Boolean) {
        _isImportant.value = isImportant
    }

    fun onListChanged(header: String, text: String, important : Boolean) {
        val newNote = Note(header, text, important)
        val list = _notes.value
        list.add(newNote)
        _notes.value = list
        SingletonList.addList(newNote)
    }
}