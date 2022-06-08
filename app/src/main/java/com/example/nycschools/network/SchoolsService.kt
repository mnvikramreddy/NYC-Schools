package com.example.nycschools.network

import com.example.nycschools.network.model.SATScoresResponse
import com.example.nycschools.network.model.SchoolsDirectoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsService {

    @GET("s3k6-pzi2")
    suspend fun getSchoolDirectoryFeed():
            List<SchoolsDirectoryResponse>

    @GET("f9bf-2cp4")
    suspend fun getSATResults(
        @Query("dbn") dbn: String
    ): List<SATScoresResponse>
}