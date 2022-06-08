package com.example.nycschools.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschools.TestDispatcherRule
import com.example.nycschools.network.SchoolsService
import com.example.nycschools.network.ServiceResponse
import com.example.nycschools.network.model.SATScoresResponse
import com.example.nycschools.network.model.SchoolsDirectoryResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SchoolsRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var schoolsService: SchoolsService

    private lateinit var schoolsRepository: SchoolsRepository

    @get:Rule
    val testDispatcher = TestDispatcherRule()


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        schoolsRepository = SchoolsRepository(schoolsService)
    }

    @Test
    fun when_getSchoolDirectory_called_error_occur_should_return_error() {
        runTest {
            coEvery {
                schoolsService.getSchoolDirectoryFeed()
            } throws Exception("error")
            val result = schoolsRepository.getSchoolDirectory()
            advanceUntilIdle()
            val resultError = result as ServiceResponse.Error

            MatcherAssert.assertThat(
                resultError.error,
                CoreMatchers.`is`("java.lang.Exception: error")
            )
        }
    }

    @Test
    fun when_getSchoolDirectory_called_error_occur_should_return_success() {
        runTest {
            coEvery {
                schoolsService.getSchoolDirectoryFeed()
            } returns schoolList
            val result = schoolsRepository.getSchoolDirectory()
            advanceUntilIdle()
            val resultSuccess = result as ServiceResponse.Success
            MatcherAssert.assertThat(resultSuccess.data, CoreMatchers.`is`(schoolList))
        }
    }

    @Test
    fun when_getSchoolSATScores_called_error_occur_should_return_error() {
        runTest {
            coEvery {
                schoolsService.getSATResults(any())
            } throws Exception("error")
            val result = schoolsRepository.getSchoolSATResults("dbn")
            advanceUntilIdle()
            val resultError = result as ServiceResponse.Error

            MatcherAssert.assertThat(
                resultError.error,
                CoreMatchers.`is`("java.lang.Exception: error")
            )
        }
    }

    @Test
    fun when_getSchoolSATScores_called_error_occur_should_return_success() {
        runTest {
            coEvery {
                schoolsService.getSATResults(any())
            } returns satScoreList
            val result = schoolsRepository.getSchoolSATResults("any")
            advanceUntilIdle()
            val resultSuccess = result as ServiceResponse.Success
            MatcherAssert.assertThat(resultSuccess.data, CoreMatchers.`is`(satScoreList))
        }
    }

    companion object {
        private val schoolList = listOf(
            SchoolsDirectoryResponse(
                schoolName = "name",
                schoolEmail = "email",
                phoneNumber = "phone",
                website = "website"
            ),
            SchoolsDirectoryResponse(
                schoolName = "name",
                schoolEmail = "email",
                phoneNumber = "phone",
                website = "website"
            ),
            SchoolsDirectoryResponse(
                schoolName = "name",
                schoolEmail = "email",
                phoneNumber = "phone",
                website = "website"
            ),
            SchoolsDirectoryResponse(
                schoolName = "name",
                schoolEmail = "email",
                phoneNumber = "phone",
                website = "website"
            )
        )

        private val satScoreList = listOf(
            SATScoresResponse(
                dbn = "dbn",
                schoolName = "name",
                numOfSatTestTakers = "123",
                satMathAvgScore = "134",
                satCriticalReadingAvgScore = "134",
                satWritingAvgScore = "234"
            )
        )
    }
}