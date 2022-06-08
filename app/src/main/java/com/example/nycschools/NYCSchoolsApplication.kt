package com.example.nycschools

import android.app.Application
import com.example.nycschools.di.component.AppComponent
import com.example.nycschools.di.component.DaggerAppComponent

class NYCSchoolsApplication : Application() {

    companion object {

        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent() {
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}