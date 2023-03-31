package com.example.noteappmvvmkotlin.UI.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappmvvmkotlin.Models.Notes
import com.example.noteappmvvmkotlin.R
import com.example.noteappmvvmkotlin.UI.Fragments.HomeFragmentDirections
import com.example.noteappmvvmkotlin.databinding.ItemsNotesBinding

class NotesAdapter(val requireContext: Context, var noteslist: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>(){

    fun filtering(newFilteredList: ArrayList<Notes>) {
        noteslist = newFilteredList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: ItemsNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.notesViewHolder {
        return notesViewHolder(ItemsNotesBinding.inflate(LayoutInflater.from(requireContext),parent,false))
    }

    override fun onBindViewHolder(holder: NotesAdapter.notesViewHolder, position: Int) {
        val data = noteslist[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubTitle.text = data.subTitle
        holder.binding.notesDate.text = data.date

        when(data.priority){
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)}
            "3" -> {holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)}
        }

        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragment2ToEditNotesFragment3(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = noteslist.size


}