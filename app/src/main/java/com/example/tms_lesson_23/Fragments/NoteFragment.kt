package com.example.tms_lesson_23.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tms_lesson_23.Model.Note
import com.example.tms_lesson_23.Model.SingletonList
import com.example.tms_lesson_23.ViewModel.MainViewModel
import com.example.tms_lesson_23.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding
        get() = _binding ?: throw RuntimeException("Fragment is null")
    private lateinit var button: Button

    private val mainVM : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button = binding.save
        super.onViewCreated(view, savedInstanceState)
        createNewNote()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createNewNote() {
        with(binding) {
            button.setOnClickListener {
                mainVM.onHeaderChanged(header.text.toString())

                mainVM.onTextChanged(text.text.toString())

                mainVM.onCheckBoxClicked(important.isChecked)

                mainVM.header.observe(viewLifecycleOwner) {
                    header.setText(it)
                }

                mainVM.text.observe(viewLifecycleOwner) {
                    text.setText(it)
                }

                mainVM.isImportant.observe(viewLifecycleOwner) {
                    important.isChecked
                }

                if (header.text.toString().isEmpty()  && text.text.toString().isEmpty()) {
                    Toast.makeText(context, "Note is empty", Toast.LENGTH_LONG).show()
                } else {
                        mainVM.onListChanged("mainVM.header.value.toString()", "mainVM.text.value.toString()",
                            false
                        )
                    Toast.makeText(context, "Note has been added", Toast.LENGTH_LONG).show()
                    binding.header.text?.clear()
                    binding.text.text?.clear()
                    binding.important.isChecked = false
                }
            }
        }
    }
}