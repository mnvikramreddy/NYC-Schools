package com.example.nycschools.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.nycschools.MainActivity
import com.example.nycschools.SharedPreferenceUtil
import com.example.nycschools.databinding.FragmentSatScoresBinding
import com.example.nycschools.viewmodels.SATScoresViewModel
import javax.inject.Inject

class SATScoresFragment : Fragment() {

    private val args: SATScoresFragmentArgs by navArgs()

    private lateinit var fragmentSatScoresBinding: FragmentSatScoresBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferenceUtil:SharedPreferenceUtil

    private val satScoresViewModel by viewModels<SATScoresViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainActivity.getActivityComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSatScoresBinding =
            FragmentSatScoresBinding.inflate(inflater, container, false).apply {
                viewModel = satScoresViewModel
                lifecycleOwner = viewLifecycleOwner
            }
        satScoresViewModel.getSchoolSATScores(args.schooldbn)
        Log.d("ACTIVITY SHARED PREFERENCE", sharedPreferenceUtil.toString())
        return fragmentSatScoresBinding.root
    }

}