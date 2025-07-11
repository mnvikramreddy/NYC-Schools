package com.example.nycschools.repositories

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
class SchoolsRepository @Inject constructor(private val schoolsService: SchoolsService) {


    suspend fun getSchoolDirectory(): ServiceResponse<List<SchoolsDirectoryResponse>> =
        try {
            ServiceResponse.Success(schoolsService.getSchoolDirectoryFeed())
        } catch (exception: Exception) {
            ServiceResponse.Error(error = exception.toString())
        }

    suspend fun getSchoolSATResults(dbn: String): ServiceResponse<List<SATScoresResponse>> {
        return try {
            ServiceResponse.Success(schoolsService.getSATResults(dbn))
        } catch (exception: Exception) {
            ServiceResponse.Error(error = exception.toString())
        }
    }
}