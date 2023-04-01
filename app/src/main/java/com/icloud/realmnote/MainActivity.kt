package com.icloud.realmnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.icloud.realmnote.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var noteDao: NoteDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("notesdb")
            .schemaVersion(1)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)

        noteDao = NoteDao()
        showNotes()


        binding.btnSave.setOnClickListener {
            val ntitle = binding.etTitle.text.toString()
            val ndetail = binding.editTextTextMultiLine2.text.toString()
            noteDao.addNote(ntitle, ndetail)

        }


        binding.btnShoList.setOnClickListener {
            showNotes()
        }

        // list view
        binding.listView.setOnItemLongClickListener { parent, view, position, id ->
            val selectedUser = parent.getItemAtPosition(position) as NoteModel
            noteDao.deleteNote(selectedUser.id)
            // showNotes()
            true
        }

    }

    fun showNotes(){
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,noteDao.getNotes())
        binding.listView.adapter = adapter
    }

}