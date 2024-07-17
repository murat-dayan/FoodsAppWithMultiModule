package com.muratdayan.foodsappwithmultimodule.di

import com.muratdayan.foodsappwithmultimodule.navigation.NavigationSubGraphs
import com.muratdayan.search.navigation.SearchFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigationSubGraphs(searchFeatureApi: SearchFeatureApi): NavigationSubGraphs{
        return NavigationSubGraphs(searchFeatureApi)
    }
}