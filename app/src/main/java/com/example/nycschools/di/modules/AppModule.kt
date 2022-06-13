package com.example.nycschools.di.modules

import android.content.Context
import androidx.room.Room
import com.example.nycschools.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * If Some external dependencies which needed at App level
 * Declared here if needed
 * like room database instance can be provided here
 * */
@Module
object AppModule {

    private const val NYC_SCHOOLS_DB = "com.sample.nyc_schools.db"

    @Singleton
    @Provides
    fun provideDatabase(context:Context):AppDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            NYC_SCHOOLS_DB
        ).build()
    }
}