package com.example.tms_lesson_23.Model

object SingletonList {
    private val list = mutableListOf(
        Note("a213", "a12d"),
        Note("111", "22"),)

    fun getList() : MutableList<Note> {
        return list
    }

    fun addList(item: Note) {
        list.add(item)
    }
}