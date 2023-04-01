package com.icloud.realmnote

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID

open class NoteModel: RealmObject() {

    @PrimaryKey
    var id = UUID.randomUUID().toString()

    var title = ""

    var noteDetail = ""
}