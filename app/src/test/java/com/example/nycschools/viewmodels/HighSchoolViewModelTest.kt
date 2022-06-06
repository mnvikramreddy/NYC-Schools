package com.example.nycschools.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschools.LiveDataUtil
import com.example.nycschools.TestDispatcherRule
import com.example.nycschools.network.ServiceResponse
import com.example.nycschools.network.model.SATScoresResponse
import com.example.nycschools.network.model.SchoolsDirectoryResponse
import com.example.nycschools.repositories.SchoolsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HighSchoolViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var schoolsRepository: SchoolsRepository

    private lateinit var schoolViewModel: HighSchoolViewModel

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun when_viewModel_initialized_getSchoolDirectory_returns_failure() =
        runTest {
            coEvery {
                schoolsRepository.getSchoolDirectory()
            } returns ServiceResponse.Error("error")
            schoolViewModel = HighSchoolViewModel(schoolsRepository)
            advanceUntilIdle()
            MatcherAssert.assertThat(LiveDataUtil.getValue(schoolViewModel.error), `is`(true))
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(schoolViewModel.schoolListUiState), `is`(
                    emptyList()
                )
            )
            MatcherAssert.assertThat(LiveDataUtil.getValue(schoolViewModel.isLoading), `is`(false))
        }

    @Test
    fun when_viewModel_initialized_getSchoolDirectory_returns_Success() =
        runTest {
            coEvery {
                schoolsRepository.getSchoolDirectory()
            } returns ServiceResponse.Success(schoolList)
            schoolViewModel = HighSchoolViewModel(schoolsRepository)
            advanceUntilIdle()
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(schoolViewModel.schoolListUiState), `is`(
                    schoolList
                )
            )
            MatcherAssert.assertThat(LiveDataUtil.getValue(schoolViewModel.error), `is`(false))
            MatcherAssert.assertThat(LiveDataUtil.getValue(schoolViewModel.isLoading), `is`(false))

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