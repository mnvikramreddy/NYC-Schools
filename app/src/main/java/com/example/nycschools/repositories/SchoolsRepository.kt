package com.example.nycschools.repositories

import android.util.Log
import com.example.nycschools.database.AppDatabase
import com.example.nycschools.di.scope.PerSchoolComponent
import com.example.nycschools.network.SchoolsService
import com.example.nycschools.network.ServiceResponse
import com.example.nycschools.network.model.SATScoresResponse
import com.example.nycschools.network.model.SchoolsDirectoryResponse
import javax.inject.Inject

/**
 * This Repository class takes care of Network Operations of school Data
 * DataBase Operations where done here if need to save to db
 * or Some business logic which is used in multiple places
 * */
@PerSchoolComponent
class SchoolsRepository @Inject constructor(private val schoolsService: SchoolsService,
                                            private val database: AppDatabase) {


    suspend fun getSchoolDirectory(): ServiceResponse<List<SchoolsDirectoryResponse>> =
        try {
            val response = ServiceResponse.Success(schoolsService.getSchoolDirectoryFeed())
            Log.d("RESPONSE", response.data.size.toString())
            database.SchoolDirectoryDao().insertSchoolDirectoryResponseList(response.data)
            response
        } catch (exception: Exception) {
            ServiceResponse.Error(error = exception.toString())
        }

    suspend fun getSchoolSATResults(dbn: String): ServiceResponse<List<SATScoresResponse>> {
        return try {
           val response = ServiceResponse.Success(schoolsService.getSATResults(dbn))
            Log.d("RESPONSE",response.data.toString())
            response
        } catch (exception: Exception) {
            ServiceResponse.Error(error = exception.toString())
        }
    }

    suspend fun getSchoolSearchList(query:String):List<SchoolsDirectoryResponse>{
       return database.SchoolDirectoryDao().getSchoolDirectorySearchList("%$query%")
    }
}