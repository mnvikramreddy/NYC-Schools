package com.example.nycschools.database.entity

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.example.nycschools.network.model.SchoolsDirectoryResponse
import com.google.gson.annotations.SerializedName

@Entity
//@Fts4(contentEntity = SchoolsDirectoryResponse::class)
data class SchoolDirectory(
    @PrimaryKey
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("school_name")
    val schoolName: String? = null,
)
