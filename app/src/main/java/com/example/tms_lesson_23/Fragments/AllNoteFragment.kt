package com.example.tms_lesson_23.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tms_lesson_23.Adapter.NoteAdapter
import com.example.tms_lesson_23.databinding.FragmentAllNoteBinding
import com.example.tms_lesson_23.Model.Note
import com.example.tms_lesson_23.Model.SingletonList
import com.example.tms_lesson_23.ViewModel.MainViewModel

class AllNoteFragment : Fragment() {
    private var _binding: FragmentAllNoteBinding? = null

    private val binding: FragmentAllNoteBinding
        get() = _binding ?: throw RuntimeException("Fragment is null")

    private val mainVM : MainViewModel by viewModels()

    private lateinit var myAdapter : NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        updateList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        myAdapter = NoteAdapter()
        binding.recycler.adapter = myAdapter
        myAdapter.submitList(ArrayList(SingletonList.getList()))
    }

    private fun updateList() {
        mainVM.notes.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
            Log.d("f", it.toString())
            myAdapter.notifyItemChanged(myAdapter.itemCount)
        }
    }
}