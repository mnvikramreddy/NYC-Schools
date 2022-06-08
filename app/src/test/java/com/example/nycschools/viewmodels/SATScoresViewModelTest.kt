package com.example.nycschools.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschools.LiveDataUtil
import com.example.nycschools.TestDispatcherRule
import com.example.nycschools.network.ServiceResponse
import com.example.nycschools.network.model.SATScoresResponse
import com.example.nycschools.repositories.SchoolsRepository
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
class SATScoresViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var schoolsRepository: SchoolsRepository

    private lateinit var satScoresViewModel: SATScoresViewModel

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        satScoresViewModel = SATScoresViewModel(schoolsRepository)

    }

    @Test
    fun when_getSATScores_returns_failure() =
        runTest {
            coEvery {
                schoolsRepository.getSchoolSATResults(any())
            } returns ServiceResponse.Error("error")
            satScoresViewModel.getSchoolSATScores("dbn")
            advanceUntilIdle()
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(satScoresViewModel.error),
                CoreMatchers.`is`(true)
            )
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(satScoresViewModel.satResultUiState), CoreMatchers.`is`(
                    SATScoresResponse()
                )
            )
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(satScoresViewModel.isLoading),
                CoreMatchers.`is`(false)
            )
        }

    @Test
    fun when_viewModel_initialized_getSchoolDirectory_returns_Success() =
        runTest {
            coEvery {
                schoolsRepository.getSchoolSATResults(any())
            } returns ServiceResponse.Success(satScoreList)
            satScoresViewModel = SATScoresViewModel(schoolsRepository)
            satScoresViewModel.getSchoolSATScores("dbn")
            advanceUntilIdle()
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(satScoresViewModel.satResultUiState), CoreMatchers.`is`(
                    satScoreList.first()
                )
            )
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(satScoresViewModel.error),
                CoreMatchers.`is`(false)
            )
            MatcherAssert.assertThat(
                LiveDataUtil.getValue(satScoresViewModel.isLoading),
                CoreMatchers.`is`(false)
            )
        }

    companion object {
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