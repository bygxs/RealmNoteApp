package com.icloud.realmnote

import io.realm.Realm

class NoteDao {
    val  db = Realm.getDefaultInstance()

    fun addNote(noteTitle: String, note: String){
        db.executeTransactionAsync {
            val note = NoteModel().apply {
                title = noteTitle
                noteDetail = note
            }
            it.insertOrUpdate(note)
        }

    }

    fun deleteNote(noteId: String){
        db.executeTransaction {
            val note = db.where(NoteModel::class.java).equalTo("id",noteId).findFirst()
            note?.deleteFromRealm()
        }

    }

    fun getNotes(): ArrayList<NoteModel>{
        val noteList = ArrayList<NoteModel>()

        noteList.addAll(db.where(NoteModel::class.java).findAllAsync())
        return noteList

    }


}