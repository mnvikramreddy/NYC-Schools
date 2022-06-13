package com.example.nycschools.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nycschools.database.dao.SchoolDirectoryDao
import com.example.nycschools.database.entity.SchoolDirectory
import com.example.nycschools.network.model.SchoolsDirectoryResponse

@Database(
    entities = [SchoolDirectory::class,SchoolsDirectoryResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun SchoolDirectoryDao(): SchoolDirectoryDao
}