package com.example.nycschools.di.component

import android.content.Context
import com.example.nycschools.di.modules.AppModule
import com.example.nycschools.di.modules.NetworkModule
import com.example.nycschools.di.modules.ViewModelFactoryBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelFactoryBuilderModule::class,
        SubComponentsModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun schoolsComponent(): SchoolsComponent.Factory
}

@Module(subcomponents = [SchoolsComponent::class])
class SubComponentsModule