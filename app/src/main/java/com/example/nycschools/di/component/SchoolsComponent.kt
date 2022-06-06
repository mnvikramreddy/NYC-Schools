package com.example.nycschools.di.component

import com.example.nycschools.MainActivity
import com.example.nycschools.di.modules.SchoolsModule
import com.example.nycschools.ui.HighSchoolsFragment
import com.example.nycschools.ui.SATScoresFragment
import dagger.Subcomponent

@Subcomponent(modules = [SchoolsModule::class])
interface SchoolsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SchoolsComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(highSchoolsFragment: HighSchoolsFragment)
    fun inject(satScoresFragment: SATScoresFragment)
}