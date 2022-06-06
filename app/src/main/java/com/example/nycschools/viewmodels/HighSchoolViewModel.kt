package com.example.nycschools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.network.ServiceResponse
import com.example.nycschools.network.model.SchoolsDirectoryResponse
import com.example.nycschools.repositories.SchoolsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HighSchoolViewModel @Inject constructor(
    private val schoolsRepository: SchoolsRepository
) : ViewModel() {

    private val _schoolListUiState = MutableLiveData<List<SchoolsDirectoryResponse>>(emptyList())
    val schoolListUiState: LiveData<List<SchoolsDirectoryResponse>> = _schoolListUiState

    private val _error = MutableLiveData<Boolean>()
    val error : LiveData<Boolean> = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getSchoolDirectoryFeed()
    }

    /**
     * If we want to get limited page instead of fetching complete list
     * we can instead paging and use paging data source or
     * Remote mediator Paging data if need to support offline
     * */
    fun getSchoolDirectoryFeed() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value=false
            val response = schoolsRepository.getSchoolDirectory()

            when (response) {
                is ServiceResponse.Success -> {
                    response.data.let { data ->
                        _schoolListUiState.value = data
                    }
                }
                is ServiceResponse.Error -> {
                    _schoolListUiState.value = emptyList()
                    _error.value = true
                }
            }
            _isLoading.value = false
        }
    }
}
