package com.example.nycschools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.network.ServiceResponse
import com.example.nycschools.network.model.SATScoresResponse
import com.example.nycschools.repositories.SchoolsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SATScoresViewModel @Inject constructor(private val schoolsRepository: SchoolsRepository) :
    ViewModel() {

    private val _satResultUiState = MutableLiveData(SATScoresResponse())
    val satResultUiState: LiveData<SATScoresResponse> = _satResultUiState

    private val _error = MutableLiveData(false)
    val error : MutableLiveData<Boolean> = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSchoolSATScores(dbn: String) {
        viewModelScope.launch {
            _isLoading.value = true

            when (val response = schoolsRepository.getSchoolSATResults(dbn = dbn)) {
                is ServiceResponse.Success -> {
                    response.data.let { data ->
                        try {
                            _satResultUiState.value = data.first()
                        }catch (e:NoSuchElementException){
                            _error.value = data.isNullOrEmpty()
                        }
                    }
                }
                is ServiceResponse.Error -> {
                    _error.value = true
                }
            }
            _isLoading.value = false
        }
    }
}
