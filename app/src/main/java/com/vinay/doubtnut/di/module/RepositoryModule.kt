package com.vinay.doubtnut.di.module

import com.vinay.doubtnut.remote.ApiService
import com.vinay.doubtnut.remote.GetNewsUseCase
import com.vinay.doubtnut.remote.GetNewsUseCaseImpl
import com.vinay.doubtnut.remote.repository.NewsRepository
import com.vinay.doubtnut.remote.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Created by VINAY on 30/05/20.
 */
@Module
class RepositoryModule {

    @Provides
    fun provideNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCaseImpl(newsRepository)
    }

    @Provides
    fun provideNewsRepository(apiService: ApiService): NewsRepository {
        return NewsRepositoryImpl(apiService)
    }
}