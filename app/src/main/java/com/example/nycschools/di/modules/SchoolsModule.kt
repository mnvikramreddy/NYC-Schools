package com.example.nycschools.di.modules

import androidx.lifecycle.ViewModel
import com.example.nycschools.viewmodels.HighSchoolViewModel
import com.example.nycschools.viewmodels.SATScoresViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SchoolsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HighSchoolViewModel::class)
    abstract fun bindHighSchoolViewModel(viewModel: HighSchoolViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SATScoresViewModel::class)
    abstract fun bindSATScoresViewModel(viewModel: SATScoresViewModel): ViewModel
}