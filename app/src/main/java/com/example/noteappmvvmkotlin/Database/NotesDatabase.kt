package com.example.noteappmvvmkotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteappmvvmkotlin.DAO.NotesDAO
import com.example.noteappmvvmkotlin.Models.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase(){
    abstract fun myNotesDao(): NotesDAO

    companion object
    {
        @Volatile
        var INSTANCE: NotesDatabase ?= null

        fun getDatabaseInstance(context: Context): NotesDatabase{
            val TempInstance = INSTANCE
            if(TempInstance != null){
                return TempInstance
            }

            synchronized(this)
            {
                val roomDatabaseInstance = Room.databaseBuilder(context, NotesDatabase::class.java, "Notes").allowMainThreadQueries().allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }

        }
    }
}