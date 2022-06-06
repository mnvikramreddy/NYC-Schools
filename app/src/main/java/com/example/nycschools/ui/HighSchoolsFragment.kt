package com.example.nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nycschools.MainActivity
import com.example.nycschools.databinding.FragmentHighSchoolsBinding
import com.example.nycschools.viewmodels.HighSchoolViewModel
import javax.inject.Inject

class HighSchoolsFragment : Fragment() {
    private lateinit var highSchoolsBinding: FragmentHighSchoolsBinding
    private lateinit var schoolsListAdapter: SchoolsListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val highSchoolViewModel by viewModels<HighSchoolViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainActivity.getActivityComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        highSchoolsBinding = FragmentHighSchoolsBinding.inflate(inflater, container, false).apply {
            viewModel = highSchoolViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        schoolsListAdapter = SchoolsListAdapter { dbn -> navigateToResultScreen(dbn) }
        swipeRefreshLayout =
            highSchoolsBinding.swipeContainer.apply {
                setOnRefreshListener {
                    highSchoolViewModel.getSchoolDirectoryFeed()
                }
            }
        recyclerView = highSchoolsBinding.storesView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schoolsListAdapter
        }
        setupSearch()
        subscribeUi()
        return highSchoolsBinding.root
    }

    /**
     * Added Search option if we used database to store
     * we can you database to filter the results
     * */
    private fun setupSearch() {
        highSchoolsBinding.searchBar.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = highSchoolViewModel.schoolListUiState.value ?: emptyList()
                schoolsListAdapter.submitList(list.filter {
                    it.schoolName?.uppercase()?.contains(newText.toString().uppercase()) == true
                })
                return true
            }
        })
    }

    private fun subscribeUi() {
        highSchoolViewModel.run {
            schoolListUiState.observe(viewLifecycleOwner, schoolsListAdapter::submitList)
            isLoading.observe(viewLifecycleOwner) { swipeRefreshLayout.isRefreshing = it }
        }
    }

    private fun navigateToResultScreen(dbn: String) {
        val directions =
            HighSchoolsFragmentDirections.actionHighSchoolsFragmentToSATScoresFragment(dbn)
        findNavController().navigate(directions)
    }

}