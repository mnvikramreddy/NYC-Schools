package com.example.nycschools.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.nycschools.database.entity.SchoolDirectory
import com.example.nycschools.network.model.SchoolsDirectoryResponse
import java.util.concurrent.Flow

@Dao
interface SchoolDirectoryDao {

    @Query("SELECT * From SchoolDirectory")
    fun getSchoolDirectoryList():LiveData<List<SchoolDirectory>>

    @Query("SELECT * From SchoolsDirectoryResponse")
    fun getSchoolDirectoryResponseList():LiveData<List<SchoolsDirectoryResponse>>

    @Query("SELECT * From SchoolsDirectoryResponse WHERE schoolName LIKE :search")
    suspend fun getSchoolDirectorySearchList(search:String):List<SchoolsDirectoryResponse>

    @Insert(onConflict = REPLACE)
    suspend fun insertSchoolDirectoryResponseList(schoolsDirectoryResponseList: List<SchoolsDirectoryResponse>)
}