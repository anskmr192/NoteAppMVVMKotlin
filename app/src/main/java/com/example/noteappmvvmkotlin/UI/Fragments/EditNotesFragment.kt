package com.example.noteappmvvmkotlin.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappmvvmkotlin.Models.Notes
import com.example.noteappmvvmkotlin.R
import com.example.noteappmvvmkotlin.ViewModels.NotesViewModel
import com.example.noteappmvvmkotlin.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {

    val notes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority:String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)


        setHasOptionsMenu(true)
        binding.edtTitle.setText(notes.data.title)
        binding.edtSubTitle.setText(notes.data.subTitle)
        binding.edtNotes.setText(notes.data.notes)

        when(notes.data.priority){
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)}
            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)}
            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)}
        }

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

        binding.NoteEditSaveBtn.setOnClickListener(){
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val Notes = binding.edtNotes.text.toString()
        if (title == "" || subTitle == "" || Notes == ""){
            Toast.makeText(it?.context,"Please fill all the details", Toast.LENGTH_SHORT).show()
        }
        else{
            val d = Date()
            val notesDate: CharSequence = DateFormat.format("MMMM ,d,yyyy",d.time)

            val data = Notes(notes.data.id,title=title,subTitle=subTitle, notes = Notes, date = notesDate.toString(),priority)
            viewModel.updateNotes(data)
            Toast.makeText(it?.context,"Notes Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editNotesFragment3_to_homeFragment2)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteMenu){
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.delete_dialog)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.YesBtn)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.NoBtn)

            textViewYes?.setOnClickListener(){
                viewModel.deleteNotes(notes.data.id!!)
                bottomSheet.dismiss()
                findNavController().navigate(R.id.action_editNotesFragment3_to_homeFragment2)
            }

            textViewNo?.setOnClickListener(){
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }



}