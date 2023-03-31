package com.example.noteappmvvmkotlin.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteappmvvmkotlin.Models.Notes
import com.example.noteappmvvmkotlin.R
import com.example.noteappmvvmkotlin.ViewModels.NotesViewModel
import com.example.noteappmvvmkotlin.databinding.FragmentCreateNotesBinding


import java.util.*


class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority:String = "1"
    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.baseline_done_24)

        binding.pGreen.setOnClickListener(){
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pYellow.setOnClickListener(){
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.pRed.setOnClickListener(){
            priority = "3"
            binding.pRed.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.noteSaveBtn.setOnClickListener(){
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.EditTitle.text.toString()
        val subTitle = binding.EditSubTitle.text.toString()
        val Notes = binding.EditNotes.text.toString()
        if (title == "" || subTitle == "" || Notes == ""){
            Toast.makeText(it?.context,"Please fill all the details",Toast.LENGTH_SHORT).show()
        }
        else{
            val d = Date()
            val notesDate: CharSequence = DateFormat.format("MMMM ,d,yyyy",d.time)

            val data = Notes(null,title=title,subTitle=subTitle, notes = Notes, date = notesDate.toString(),priority)
            viewModel.addNotes(data)
            Toast.makeText(it?.context,"Notes Created Successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createNotesFragment2_to_homeFragment2)
        }

    }


}