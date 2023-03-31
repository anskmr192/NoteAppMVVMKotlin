package com.example.noteappmvvmkotlin.UI.Fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noteappmvvmkotlin.Models.Notes
import com.example.noteappmvvmkotlin.R
import com.example.noteappmvvmkotlin.UI.Adapters.NotesAdapter
import com.example.noteappmvvmkotlin.ViewModels.NotesViewModel
import com.example.noteappmvvmkotlin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel:NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container,false)

        setHasOptionsMenu(true)

        // get all notes
        viewModel.getNotes().observe(viewLifecycleOwner,{noteslist->
            oldMyNotes = noteslist as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(),noteslist)
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
            binding.rcvAllNotes.adapter = adapter
        })

        // get all notes from filter
        binding.FilterBtn.setOnClickListener(){
            viewModel.getNotes().observe(viewLifecycleOwner,{noteslist->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        // get all high notes
        binding.FilterHigh.setOnClickListener(){
            viewModel.getHighNotes().observe(viewLifecycleOwner,{noteslist->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })

        }

        //get all medium notes
        binding.FilterMedium.setOnClickListener(){
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{noteslist->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })

        }

        // get all low notes
        binding.FilterLow.setOnClickListener(){
            viewModel.getLowNotes().observe(viewLifecycleOwner,{noteslist->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })

        }

        binding.btnAddNOtes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_createNotesFragment2)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter notes here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(newText: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for(i in oldMyNotes ){
            if(i.title.contains(newText!!) || i.subTitle.contains(newText!!) || i.notes.contains(newText!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}

