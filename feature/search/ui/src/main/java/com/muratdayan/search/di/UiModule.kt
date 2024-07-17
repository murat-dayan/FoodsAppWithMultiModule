package com.muratdayan.search.di

import com.muratdayan.search.navigation.SearchFeatureApi
import com.muratdayan.search.navigation.SearchFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    fun provideSearchFeatureApi(): SearchFeatureApi {
        return SearchFeatureImpl()
    }
}